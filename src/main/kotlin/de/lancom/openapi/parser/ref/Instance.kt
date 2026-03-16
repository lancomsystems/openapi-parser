package de.lancom.openapi.parser.ref

import tools.jackson.core.JsonGenerator
import tools.jackson.databind.SerializationContext
import tools.jackson.databind.annotation.JsonSerialize
import tools.jackson.databind.ser.std.StdSerializer

@JsonSerialize(using = Instance.Companion.Serializer::class)
data class Instance<R : Referenceable>(
    val referenced: R,
) : ReferenceOrInstance<R> {
    companion object {
        class Serializer : StdSerializer<Instance<*>>(Instance::class.java) {
            override fun serialize(value: Instance<*>, gen: JsonGenerator, provider: SerializationContext) {
                gen.writePOJO(value.referenced)
            }
        }
    }
}
