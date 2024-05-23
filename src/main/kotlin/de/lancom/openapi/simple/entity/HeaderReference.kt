package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class HeaderReference(
    override val parsedReference: ValidParsedReference
) : Reference, HeaderOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): HeaderReference {
            return HeaderReference(ValidParsedReference(ComponentType.Headers, name))
        }
    }
}
