package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class ParameterReference(
    override val parsedReference: ValidParsedReference
) : Reference, ParameterOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    override fun toString(): String {
        return parsedReference.toString()
    }

    companion object {
        fun fromName(name: String): ParameterReference {
            return ParameterReference(ValidParsedReference(ComponentType.Parameters, name))
        }
    }
}
