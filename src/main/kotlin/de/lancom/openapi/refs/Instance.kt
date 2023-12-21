package de.lancom.openapi.refs

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.StdSerializer

@JsonSerialize(using = Instance.Companion.Serializer::class)
data class Instance<R : Referenceable>(
    val referenced: R,
) : ReferenceOrInstance<R> {
    companion object {
        class Serializer : StdSerializer<Instance<*>>(Instance::class.java) {
            override fun serialize(value: Instance<*>?, gen: JsonGenerator?, provider: SerializerProvider?) {
                gen!!.writeObject(value!!.referenced)
            }
        }
    }
}
