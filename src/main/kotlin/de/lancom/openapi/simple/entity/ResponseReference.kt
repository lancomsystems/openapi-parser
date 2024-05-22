package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class ResponseReference(
    override val parsedReference: ValidParsedReference
) : Reference, ResponseOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): ResponseReference {
            return ResponseReference(ValidParsedReference(ComponentType.Responses, name))
        }
    }
}
