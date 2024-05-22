package de.lancom.openapi.parser.jackson

import com.fasterxml.jackson.databind.node.ObjectNode
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.entity.Extension
import de.lancom.openapi.parser.field.Field

fun <E : Entity> extensionParser(
    wrapper: Wrapper,
    parser: (Wrapper, Field<Map<String, Extension?>>, fields: Set<String>) -> E,
): E {
    val extensions = wrapper.getExtensions().orNull
    return if (extensions == null) {
        parser(wrapper, Field.unset(), wrapper.fieldOrder)
    } else {
        val objectNode: ObjectNode = wrapper.jsonNodeField.getOrError().deepCopy()
        extensions.keys.forEach { extension ->
            objectNode.remove(extension)
        }
        parser(Wrapper(objectNode), Field(extensions), wrapper.fieldOrder)
    }
}
