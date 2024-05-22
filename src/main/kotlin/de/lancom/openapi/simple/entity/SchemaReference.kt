package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference

data class SchemaReference(
    override val parsedReference: ValidParsedReference
) : Reference, SchemaOrRef {
    init {
        if (parsedReference.componentType != this.componentType) {
            throw IllegalArgumentException("invalid component type ${parsedReference.componentType}!")
        }
    }

    companion object {
        fun fromName(name: String): SchemaReference {
            return SchemaReference(ValidParsedReference(ComponentType.Schemas, name))
        }
    }
}
