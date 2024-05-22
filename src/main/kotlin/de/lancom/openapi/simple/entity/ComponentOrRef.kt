package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType

sealed interface ComponentOrRef {
    val componentType: ComponentType
}
