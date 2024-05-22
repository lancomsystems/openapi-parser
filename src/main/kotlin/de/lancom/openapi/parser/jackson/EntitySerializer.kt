package de.lancom.openapi.parser.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.parser.entity.AdditionalPropertiesBoolean
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.getFields

abstract class EntitySerializer<E : Entity>(
    clazz: Class<E>,
    val parser: Parser<E>,
) : StdSerializer<E>(clazz) {
    override fun serialize(value: E?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value == null) {
            gen!!.writeNull()
        } else if (value is AdditionalPropertiesBoolean) {
            gen!!.writeObject(true)
        } else if (value is JsonWrapper) {
            gen!!.writeObject(value.key)
        } else {
            val entityDescriptor = value.entityDescriptor
            val entityJson = entityDescriptor.jsonNode
            if (entityJson != null) {
                if (entityJson.isDefined) {
                    gen!!.writeObject(entityJson.orNull)
                } else {
                    gen!!.writeStartObject()
                    gen.writeEndObject()
                }
            } else {
                val flatten = entityDescriptor.flatten.getFields().filter { entry ->
                    entry !is Map<*, *>
                }
                val size = flatten.size
                when (size) {
                    0 ->
                        gen!!.writeObject(entityDescriptor.entityMap.sorted(entityDescriptor.order))

                    1 ->
                        gen!!.writeObject(flatten.first())

                    else ->
                        TODO()
                }
            }
        }
    }
}

private fun <K, V> Map<K, V>.sorted(order: List<String>): Map<K, V> {
    val orderSet: Set<K> = order.map { key ->
        @Suppress("UNCHECKED_CAST")
        key as K
    }.toSet()

    val ordered = orderSet.mapNotNull { key ->
        if (containsKey(key)) {
            @Suppress("UNCHECKED_CAST")
            key to get(key) as V
        } else {
            null
        }
    }.toMap()

    val other = filter { (key, _) ->
        !orderSet.contains(key)
    }

    return ordered + other
}
