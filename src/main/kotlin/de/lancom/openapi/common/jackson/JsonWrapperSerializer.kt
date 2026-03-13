package de.lancom.openapi.common.jackson

import tools.jackson.core.JsonGenerator
import tools.jackson.databind.SerializationContext
import tools.jackson.databind.ser.std.StdSerializer

abstract class JsonWrapperSerializer<JW : JsonWrapper>(
    clazz: Class<JW>,
) : StdSerializer<JW>(clazz) {
    override fun serialize(
        value: JW,
        gen: JsonGenerator,
        serializers: SerializationContext,
    ) {
        gen.writeString(value.key)
    }
}
