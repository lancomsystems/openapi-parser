package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class RequestBodyReference(
    override val parsedReference: ValidParsedReference
) : Reference, RequestBodyOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    override fun toString(): String {
        return parsedReference.toString()
    }

    companion object {
        fun fromName(name: String): RequestBodyReference {
            return RequestBodyReference(ValidParsedReference(ComponentType.RequestBodies, name))
        }
    }
}
