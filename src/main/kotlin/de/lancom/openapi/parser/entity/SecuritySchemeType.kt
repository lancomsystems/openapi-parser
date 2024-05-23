package de.lancom.openapi.parser.entity

import de.lancom.openapi.parser.jackson.ReferenceParser

enum class SecuritySchemeType(
    val parser: ReferenceParser<out SecurityScheme>
) {
    apiKey(APIKeySecurityScheme),
    http(HTTPSecurityScheme),
    oauth2(OAuth2SecurityScheme),
    openIdConnect(OpenIdConnectSecurityScheme),
    ;
}
