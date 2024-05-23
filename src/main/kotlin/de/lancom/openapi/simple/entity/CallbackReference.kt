package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class CallbackReference(
    override val parsedReference: ValidParsedReference
) : Reference, CallbackOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): CallbackReference {
            return CallbackReference(ValidParsedReference(ComponentType.Callbacks, name))
        }
    }
}
