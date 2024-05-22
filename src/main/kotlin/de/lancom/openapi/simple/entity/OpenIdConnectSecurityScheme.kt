package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension

data class OpenIdConnectSecurityScheme(
    val openIdConnectUrl: String? = null,
    val description: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: SecuritySchemeReference? = null,
) : Component<OpenIdConnectSecurityScheme, SecuritySchemeReference>, SecuritySchemeOrRef {
    override fun addComponentName(name: String): OpenIdConnectSecurityScheme {
        return addComponentReference(componentReference = SecuritySchemeReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SecuritySchemeReference): OpenIdConnectSecurityScheme {
        return copy(componentReference = componentReference)
    }
}
