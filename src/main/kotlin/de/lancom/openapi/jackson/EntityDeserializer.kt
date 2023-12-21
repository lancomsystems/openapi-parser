package de.lancom.openapi.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.lancom.openapi.entity.Entity

abstract class EntityDeserializer<E : Entity>(
    clazz: Class<E>,
    val parser: Parser<E>,
) : StdDeserializer<E>(clazz) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): E {
        val node: JsonNode = p!!.codec!!.readTree(p)!!
        return parser.parseJsonNode(node)
    }
}
