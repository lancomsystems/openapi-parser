package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class ServerReference(
    override val parsedReference: ValidParsedReference
) : de.lancom.openapi.simple.entity.Reference, de.lancom.openapi.simple.entity.ServerOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    override fun toString(): String {
        return parsedReference.toString()
    }

    companion object {
        fun fromName(name: String): de.lancom.openapi.simple.entity.ServerReference {
            return de.lancom.openapi.simple.entity.ServerReference(ValidParsedReference(ComponentType.Servers, name))
        }
    }
}
