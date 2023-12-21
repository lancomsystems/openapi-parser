package de.lancom.openapi.jackson

import com.fasterxml.jackson.databind.node.ObjectNode
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.entity.Extension
import de.lancom.openapi.field.Field

fun <E : Entity> extensionParser(wrapper: Wrapper, parser: (Wrapper, Field<Map<String, Extension?>>) -> E): E {
    val extensions = wrapper.getExtensions().orNull
    return if (extensions == null) {
        parser(wrapper, Field.unset())
    } else {
        val objectNode: ObjectNode = wrapper.jsonNodeField.getOrError().deepCopy()
        extensions.keys.forEach { extension ->
            objectNode.remove(extension)
        }
        parser(Wrapper(objectNode), Field(extensions))
    }
}
