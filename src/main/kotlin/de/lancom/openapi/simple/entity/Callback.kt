package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.simple.types.Extension

data class Callback(
    val pathItems: Map<String, PathItem> = emptyMap(),
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: CallbackReference? = null,
) : Component<Callback, CallbackReference>, CallbackOrRef {
    override fun addComponentName(name: String): Callback {
        return addComponentReference(componentReference = CallbackReference.fromName(name))
    }

    override fun addComponentReference(componentReference: CallbackReference): Callback {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<CallbackOrRef> {
        override val componentType: ComponentType = ComponentType.Callbacks
    }
}
