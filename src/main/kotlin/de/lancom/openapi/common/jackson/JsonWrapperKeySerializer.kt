package de.lancom.openapi.common.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

abstract class JsonWrapperKeySerializer<JW : JsonWrapper>(
    clazz: Class<JW>,
) : StdSerializer<JW>(clazz) {
    override fun serialize(
        value: JW,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        gen.writeFieldName(value.key)
    }
}
