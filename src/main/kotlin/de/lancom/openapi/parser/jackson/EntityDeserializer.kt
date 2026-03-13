package de.lancom.openapi.parser.jackson

import tools.jackson.core.JsonParser
import tools.jackson.databind.DeserializationContext
import tools.jackson.databind.JsonNode
import tools.jackson.databind.deser.std.StdDeserializer
import de.lancom.openapi.parser.entity.Entity

abstract class EntityDeserializer<E : Entity>(
    clazz: Class<E>,
    val parser: Parser<E>,
) : StdDeserializer<E>(clazz) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): E {
        val node: JsonNode = p.readValueAsTree()
        return parser.parseJsonNode(node)
    }
}
