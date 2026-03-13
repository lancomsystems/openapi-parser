package de.lancom.openapi.parser.jackson

import tools.jackson.core.JsonGenerator
import tools.jackson.databind.JsonNode
import tools.jackson.databind.SerializationContext
import tools.jackson.databind.ser.std.StdSerializer
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.parser.entity.AdditionalPropertiesBoolean
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.getFields

abstract class EntitySerializer<E : Entity>(
    clazz: Class<E>,
    val parser: Parser<E>,
) : StdSerializer<E>(clazz) {
    override fun serialize(value: E, gen: JsonGenerator, provider: SerializationContext) {
        if (value is AdditionalPropertiesBoolean) {
            gen.writePOJO(true)
        } else if (value is JsonWrapper) {
            gen.writePOJO(value.key)
        } else {
            val entityDescriptor = value.entityDescriptor
            val entityJson = entityDescriptor.jsonNode
            if (entityJson != null) {
                if (entityJson.isDefined) {
                    gen.writeTree(entityJson.orNull as JsonNode)
                } else {
                    gen.writeStartObject()
                    gen.writeEndObject()
                }
            } else {
                val flatten = entityDescriptor.flatten.getFields().filter { entry ->
                    entry !is Map<*, *>
                }
                val size = flatten.size
                when (size) {
                    0 ->
                        gen.writePOJO(entityDescriptor.entityMap.sorted(entityDescriptor.order))

                    1 ->
                        gen.writePOJO(flatten.first())

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
