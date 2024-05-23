package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType

sealed interface ServerOrRef : de.lancom.openapi.simple.entity.ComponentOrRef {
    override val componentType: ComponentType
        get() = ComponentType.Servers
}
