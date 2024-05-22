package de.lancom.openapi.parser.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import de.lancom.openapi.common.util.*
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.entity.Extension
import de.lancom.openapi.parser.entity.RawExtension
import de.lancom.openapi.parser.entity.TagGroupsExtension
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.field.getFields
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Reference
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

data class Wrapper(
    val jsonNodeField: Field<JsonNode>,
) {
    val fieldOrder: Set<String> by lazy {
        jsonNodeField.orNull?.fields()?.iterator()?.asSequence()?.toList()?.map { (key, _) ->
            key
        }?.toSet() ?: emptySet()
    }
    val field: Field<Wrapper> by lazy {
        jsonNodeField.mapField(::Wrapper)
    }

    val mapField: Field<Map<String, Wrapper>> by lazy {
        jsonNodeField.map { jsonNode ->
            jsonNode.fields().iterator().asSequence().toList().associate { (k, v) ->
                k to Wrapper(v)
            }
        }
    }

    val listField: Field<List<Wrapper>> by lazy {
        jsonNodeField.map { jsonNode ->
            jsonNode.elements().iterator().asSequence().toList().map(Field.Companion::invoke).map(::Wrapper)
        }
    }

    val jsonNodeFieldOrUnsetIfEmpty: Field<JsonNode> by lazy {
        jsonNodeField.takeUnlessField { jsonNode ->
            jsonNode is ObjectNode && jsonNode.isEmpty
        }
    }

    operator fun get(fieldName: String): Wrapper {
        val wrapperField = mapField.flatMap { map: Map<String, Wrapper> ->
            Field.unsetIfNull(map[fieldName])
        }
        return wrapperField.getOrElse {
            UNSET
        }
    }

    fun <V> getMap(
        mapper: Wrapper.() -> Field<V>,
    ): Field<Map<String, V>> {
        return getMap({ key -> key }, mapper)
    }

    fun <K, V> getMap(
        keyMapper: (String) -> K,
        valueMapper: Wrapper.() -> Field<V>,
    ): Field<Map<K, V>> {
        return mapField.map { map: Map<String, Wrapper> ->
            map.map { (key, wrapper) ->
                keyMapper(key) to valueMapper(wrapper)
            }.toMap().getFields()
        }
    }

    fun <V> getList(
        mapper: Wrapper.() -> Field<V>,
    ): Field<List<V>> {
        return listField.map { list: List<Wrapper> ->
            list.map { wrapper ->
                wrapper.mapper()
            }.getFields()
        }
    }

    fun <V> getSingle(
        mapper: Wrapper.() -> Field<V>,
    ): Field<V> {
        return mapField.flatMap {
            this.mapper()
        }
    }

    fun <K, V> getUnlessEmpty(
        mapper: Wrapper.() -> Field<Map<K, V>>,
    ): Field<Map<K, V>> {
        return mapField.flatMap {
            this.mapper().flatMap { map: Map<K, V> ->
                Field.takeUnlessEmpty(map)
            }
        }
    }

    fun <V> getSet(
        mapper: Wrapper.() -> Field<V>,
    ): Field<Set<V>> {
        return getList(mapper).map(List<V>::toSet)
    }

    private fun <E> getNull(): Field<E?> {
        return jsonNodeField.flatMap { jsonNode ->
            if (jsonNode.isNull) {
                Field(null)
            } else {
                Field.unset()
            }
        }
    }

    fun <E : Collection<E>> getNullOrElseUnsetIfEmptyCollection(fallback: Wrapper.() -> Field<E>): Field<E?> {
        return getNullOrElse {
            fallback().flatMap { collection ->
                Field.unsetIfNull(collection.takeUnlessEmpty())
            }
        }
    }

    fun <K, V> getNullOrElseUnsetIfEmptyMap(fallback: Wrapper.() -> Field<Map<K, V>>): Field<Map<K, V>?> {
        return getNullOrElse {
            fallback().flatMap { collection ->
                Field.unsetIfNull(collection.takeUnlessEmpty())
            }
        }
    }

    fun <E> getNullOrElse(fallback: Wrapper.() -> Field<E>): Field<E?> {
        return getNull<E>().orElse {
            @Suppress("UNCHECKED_CAST")
            getElse(fallback) as Field<E?>
        }
    }

    fun <E> getElse(fallback: Wrapper.() -> Field<E>): Field<E> {
        return jsonNodeField.flatMap {
            this.fallback()
        }
    }

    fun <E : Entity> getEntity(
        transform: Wrapper.() -> E,
    ): Field<E> {
        return mapField.map {
            transform(this)
        }
    }

    fun <R : Referenceable> getReference(): Field<Reference<R>> {
        return mapField.flatMap { mapped ->
            if (mapped.size == 1) {
                this["\$ref"].getString().map { ref ->
                    Reference(ref)
                }
            } else {
                Field.unset()
            }
        }
    }

    fun <R : Referenceable> getReferenceOrEntity(
        transform: Wrapper.() -> R
    ): Field<ReferenceOrInstance<R>> {
        return getReference<R>().map { ref: ReferenceOrInstance<R> ->
            ref
        }.orElse {
            getEntity(transform).map { entity: R ->
                Instance(entity)
            }
        }
    }

    fun getBoolean(): Field<Boolean> {
        return jsonNodeField.map(JsonNode::booleanValueOrError)
    }

    fun getString(): Field<String> {
        return jsonNodeField.map { jsonNode ->
            when {
                // parse boolean as string...
                jsonNode.isBoolean ->
                    jsonNode.booleanValue().toString()

                // parse number as string...
                jsonNode.isNumber ->
                    jsonNode.numberValue().toString()

                else ->
                    jsonNode.textValueOrError()
            }
        }
    }

    fun getNumber(): Field<Number> {
        return jsonNodeField.map(JsonNode::numberValueOrError)
    }

    fun getInt(): Field<Int> {
        return getNumber().map { number: Number ->
            number.toInt()
        }
    }

    inline fun <reified E : Enum<E>> getEnum(
        noinline transform: (String) -> E,
    ): Field<E> {
        return getString().map(transform)
    }

    fun getExtensions(): Field<Map<String, Extension?>> {
        return mapField.flatMap { mapped ->
            val extensions = mapped.filter { (name, _) ->
                name.startsWith("x-")
            }.map { (name, wrapper) ->
                wrapper.jsonNodeField.map { value ->
                    val extension = when {
                        // TODO hardcoded :(
                        name == "x-tagGroups" ->
                            TagGroupsExtension.parseWrapper(wrapper)

                        value.isNull ->
                            null

                        value.isNumber || value.isTextual || value.isBoolean || value.isArray || value.isObject ->
                            RawExtension(Field(value))

                        else ->
                            throw NotImplementedError()
                    }
                    name to extension
                }
            }

            Field.unsetIfNull(
                extensions
                    .getFields()
                    .toMap()
                    .takeUnlessEmpty()
            )
        }
    }

    companion object {
        operator fun invoke(jsonNode: JsonNode): Wrapper {
            return Wrapper(Field(jsonNode))
        }

        val UNSET: Wrapper = Wrapper(Field.unset())

        fun parseJsonString(json: String): Wrapper {
            val tree = jsonMapper.readTree(json)
            return Wrapper(tree)
        }
    }
}
