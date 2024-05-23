package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class OperationReference(
    override val parsedReference: ValidParsedReference
) : Reference, OperationOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): OperationReference {
            return OperationReference(ValidParsedReference(ComponentType.Operations, name))
        }
    }
}
