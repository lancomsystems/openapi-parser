package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType

// used for references to a SecurityScheme where the type is not know yet
data class SecurityScheme(
    override val componentReference: SecuritySchemeReference? = null,
) : Component<SecurityScheme, SecuritySchemeReference>, SecuritySchemeOrRef {
    override fun addComponentName(name: String): SecurityScheme {
        return addComponentReference(componentReference = SecuritySchemeReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SecuritySchemeReference): SecurityScheme {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<SecuritySchemeOrRef> {
        override val componentType: ComponentType = ComponentType.SecuritySchemes
    }
}
