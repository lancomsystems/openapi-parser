package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldSecuritySchemeType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val securitySchemeOpenIdConnectSecurityScheme = OpenApiEntity(
    entityType = EntityType.OpenIdConnectSecurityScheme,
    baseType = "SecurityScheme",
    emptyYaml = "type: openIdConnect",
    fields = listOf(
        fieldSecuritySchemeType.fixed("SecuritySchemeType.openIdConnect").name("type"),
        fieldString.name("openIdConnectUrl"),
        fieldString.name("description"),
    )
)
