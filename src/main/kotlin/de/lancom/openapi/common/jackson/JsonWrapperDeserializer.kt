package de.lancom.openapi.common.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

abstract class JsonWrapperDeserializer<JW : JsonWrapper>(
    clazz: Class<JW>,
    private val factory: (String) -> JW?
) : StdDeserializer<JW>(clazz) {
    override fun deserialize(parser: JsonParser, ctxt: DeserializationContext?): JW? {
        return factory(parser.text)
    }
}
