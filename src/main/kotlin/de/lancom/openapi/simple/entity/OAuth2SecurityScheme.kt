package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension

data class OAuth2SecurityScheme(
    val description: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: SecuritySchemeReference? = null,
) : Component<OAuth2SecurityScheme, SecuritySchemeReference>, SecuritySchemeOrRef {
    override fun addComponentName(name: String): OAuth2SecurityScheme {
        return addComponentReference(componentReference = SecuritySchemeReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SecuritySchemeReference): OAuth2SecurityScheme {
        return copy(componentReference = componentReference)
    }
}
