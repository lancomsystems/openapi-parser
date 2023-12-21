package de.lancom.openapi.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import de.lancom.openapi.entity.AdditionalPropertiesBoolean
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.getFields

abstract class EntitySerializer<E : Entity>(
    clazz: Class<E>,
    val parser: Parser<E>,
) : StdSerializer<E>(clazz) {
    override fun serialize(value: E?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value == null) {
            gen!!.writeNull()
        } else if (value is AdditionalPropertiesBoolean) {
            gen!!.writeObject(true)
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
                        gen!!.writeObject(entityDescriptor.entityMap.sorted())

                    1 ->
                        gen!!.writeObject(flatten.first())

                    else ->
                        TODO()
                }
            }
        }
    }
}

private fun <K, V> Map<K, V>.sorted(): Map<K, V> {
    return toList()
        .sortedBy { (key, _) ->
            key.toString()
        }
        .toMap()
}
