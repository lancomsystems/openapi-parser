package de.lancom.openapi.simple.entity

sealed interface Component<Self : ComponentOrRef, Ref : Reference> : ComponentOrRef {
    val componentReference: Reference?

    fun addComponentName(name: String): Self

    fun addComponentReference(componentReference: Ref): Self
}
