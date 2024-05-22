package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.InvalidParsedReference
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.common.util.ValidParsedReference

sealed interface ReferenceableCompanion<R : ComponentOrRef> {
    val componentType: ComponentType

    fun fromReference(parsedReference: ParsedReference): R {
        return when (parsedReference) {
            is ValidParsedReference -> {
                val ct = parsedReference.componentType
                val name = parsedReference.name
                if (parsedReference.componentType == componentType) {
                    val reference = Reference(ct, name)
                    @Suppress("UNCHECKED_CAST")
                    reference as R
                } else {
                    throw IllegalArgumentException("expected a $componentType reference but found a $ct reference: $name")
                }
            }

            is InvalidParsedReference ->
                @Suppress("UNCHECKED_CAST")
                InvalidReference(parsedReference) as R
        }
    }
}
