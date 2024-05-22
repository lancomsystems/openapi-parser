package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension

data class HTTPSecurityScheme(
    val scheme: String? = null,
    val bearerFormat: String? = null,
    val description: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: SecuritySchemeReference? = null,
) : Component<HTTPSecurityScheme, SecuritySchemeReference>, SecuritySchemeOrRef {
    override fun addComponentName(name: String): HTTPSecurityScheme {
        return addComponentReference(componentReference = SecuritySchemeReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SecuritySchemeReference): HTTPSecurityScheme {
        return copy(componentReference = componentReference)
    }
}
