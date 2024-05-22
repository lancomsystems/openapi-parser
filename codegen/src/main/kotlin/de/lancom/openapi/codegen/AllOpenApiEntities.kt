package de.lancom.openapi.codegen

import de.lancom.openapi.codegen.entity.*
import de.lancom.openapi.codegen.view.OpenApiEntity

val allOpenApiEntities: List<OpenApiEntity> = listOf(
    additionalPropertiesBoolean,
    additionalPropertiesReference,
    additionalPropertiesSchema,
    callback,
    components,
    contact,
    encoding,
    example,
    header,
    info,
    license,
    link,
    mediaType,
    openApi,
    operation,
    parameter,
    pathItem,
    paths,
    requestBody,
    response,
    responses,
    schema,
    securitySchemeAPIKeySecurityScheme,
    securitySchemeHTTPSecurityScheme,
    securitySchemeOAuth2SecurityScheme,
    securitySchemeOpenIdConnectSecurityScheme,
    server,
    serverVariable,
    tag,
) + jsonEntities + listOf(
    rawExtension,
    tagGoupsExtension,
    tagGoupsExtensionEntry,
)
