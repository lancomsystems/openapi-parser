package de.lancom.openapi.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

abstract class KeySerializer<K : Key>(
    clazz: Class<K>,
) : StdSerializer<K>(clazz) {
    override fun serialize(value: K?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeFieldName(value?.key)
    }
}
