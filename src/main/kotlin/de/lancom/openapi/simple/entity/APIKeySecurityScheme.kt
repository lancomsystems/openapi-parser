package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.InApiKey
import de.lancom.openapi.simple.types.Extension

data class APIKeySecurityScheme(
    val name: String? = null,
    val `in`: InApiKey? = null,
    val description: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: SecuritySchemeReference? = null,
) : Component<APIKeySecurityScheme, SecuritySchemeReference>, SecuritySchemeOrRef {
    override fun addComponentName(name: String): APIKeySecurityScheme {
        return addComponentReference(componentReference = SecuritySchemeReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SecuritySchemeReference): APIKeySecurityScheme {
        return copy(componentReference = componentReference)
    }
}
