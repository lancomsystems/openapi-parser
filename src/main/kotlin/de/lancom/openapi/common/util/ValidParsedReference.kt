package de.lancom.openapi.common.util

import de.lancom.openapi.common.types.ComponentType

data class ValidParsedReference(
    val componentType: ComponentType,
    val name: String,
) : ParsedReference {
    override val ref: String = "#/components/${componentType.name.lowercase()}/$name"

    override fun toString(): String {
        return ref
    }
}
