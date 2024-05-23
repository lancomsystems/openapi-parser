package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class SecuritySchemeReference(
    override val parsedReference: ValidParsedReference
) : Reference, SecuritySchemeOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): SecuritySchemeReference {
            return SecuritySchemeReference(ValidParsedReference(ComponentType.SecuritySchemes, name))
        }
    }
}
