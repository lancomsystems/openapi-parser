package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldSecuritySchemeType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val securitySchemeHTTPSecurityScheme = OpenApiEntity(
    entityType = EntityType.HTTPSecurityScheme,
    baseType = "SecurityScheme",
    emptyYaml = "type: http",
    fields = listOf(
        fieldString.name("scheme"),
        fieldString.name("bearerFormat"),
        fieldString.name("description"),
        fieldSecuritySchemeType.fixed("SecuritySchemeType.http").name("type"),
    )
)
