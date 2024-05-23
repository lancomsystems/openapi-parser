package de.lancom.openapi.common.converter.simple

fun toSimple(
    referenceOrInstance: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.SecurityScheme>
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return toSimple(referenceOrInstance, ::toSimple, de.lancom.openapi.simple.entity.SecurityScheme::fromReference)
}

fun toSimple(
    securityScheme: de.lancom.openapi.parser.entity.SecurityScheme,
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return when (securityScheme) {
        is de.lancom.openapi.parser.entity.APIKeySecurityScheme ->
            toSimple(securityScheme)

        is de.lancom.openapi.parser.entity.HTTPSecurityScheme ->
            toSimple(securityScheme)

        is de.lancom.openapi.parser.entity.OAuth2SecurityScheme ->
            toSimple(securityScheme)

        is de.lancom.openapi.parser.entity.OpenIdConnectSecurityScheme ->
            toSimple(securityScheme)
    }
}

fun toSimple(
    securityScheme: de.lancom.openapi.parser.entity.APIKeySecurityScheme,
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return de.lancom.openapi.simple.entity.APIKeySecurityScheme(
        name = securityScheme.name,
        `in` = securityScheme.`in`,
        description = securityScheme.description,
        extensions = securityScheme.extensions.toSimple(),
    )
}

fun toSimple(
    securityScheme: de.lancom.openapi.parser.entity.HTTPSecurityScheme,
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return de.lancom.openapi.simple.entity.HTTPSecurityScheme(
        scheme = securityScheme.scheme,
        bearerFormat = securityScheme.bearerFormat,
        description = securityScheme.description,
        extensions = securityScheme.extensions.toSimple(),
    )
}

fun toSimple(
    securityScheme: de.lancom.openapi.parser.entity.OAuth2SecurityScheme,
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return de.lancom.openapi.simple.entity.OAuth2SecurityScheme(
        description = securityScheme.description,
        extensions = securityScheme.extensions.toSimple(),
    )
}


fun toSimple(
    securityScheme: de.lancom.openapi.parser.entity.OpenIdConnectSecurityScheme,
): de.lancom.openapi.simple.entity.SecuritySchemeOrRef {
    return de.lancom.openapi.simple.entity.OpenIdConnectSecurityScheme(
        openIdConnectUrl = securityScheme.openIdConnectUrl,
        description = securityScheme.description,
        extensions = securityScheme.extensions.toSimple(),
    )
}
