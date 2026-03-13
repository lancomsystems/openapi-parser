package de.lancom.openapi.common.jackson

import tools.jackson.core.JsonParser
import tools.jackson.databind.DeserializationContext
import tools.jackson.databind.deser.std.StdDeserializer

abstract class JsonWrapperDeserializer<JW : JsonWrapper>(
    clazz: Class<JW>,
    private val factory: (String) -> JW?
) : StdDeserializer<JW>(clazz) {
    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext): JW {
        return requireNotNull(factory(parser.text)) {
            "Unknown JsonWrapper key: ${parser.text}"
        }
    }
}
