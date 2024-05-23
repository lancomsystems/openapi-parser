package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType

sealed interface RequestBodyOrRef : ComponentOrRef {
    override val componentType: ComponentType
        get() = ComponentType.RequestBodies
}
