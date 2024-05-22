package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class PathItemReference(
    override val parsedReference: ValidParsedReference
) : Reference, PathItemOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): PathItemReference {
            return PathItemReference(ValidParsedReference(ComponentType.PathItems, name))
        }
    }
}
