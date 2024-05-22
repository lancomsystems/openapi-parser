package de.lancom.openapi.common.util

import de.lancom.openapi.common.types.ComponentType

sealed interface ParsedReference {
    val ref: String?

    companion object {
        operator fun invoke(reference: String?): ParsedReference {
            val parts = reference?.split("/", limit = 4)
            if (parts != null && parts.size == 4 && parts[0] == "#" && parts[1] == "components") {
                val componentType = ComponentType.byKey[parts[2]]
                if (componentType != null) {
                    return ValidParsedReference(componentType, parts[3])
                }
            }
            return InvalidParsedReference(reference)
        }
    }
}
