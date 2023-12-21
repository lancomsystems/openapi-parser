package de.lancom.openapi.entity

import de.lancom.openapi.jackson.ReferenceParser

enum class SecuritySchemeType(
    val parser: ReferenceParser<out SecurityScheme>
) {
    apiKey(APIKeySecurityScheme),
    http(HTTPSecurityScheme),
    oauth2(OAuth2SecurityScheme),
    openIdConnect(OpenIdConnectSecurityScheme),
    ;
}
