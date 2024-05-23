package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldInApiKey
import de.lancom.openapi.codegen.field.fieldSecuritySchemeType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val securitySchemeAPIKeySecurityScheme = OpenApiEntity(
    entityType = EntityType.APIKeySecurityScheme,
    baseType = "SecurityScheme",
    emptyYaml = "type: apiKey",
    fields = listOf(
        fieldSecuritySchemeType.fixed("SecuritySchemeType.apiKey").name("type"),
        fieldString.name("name"),
        fieldInApiKey.name("in"),
        fieldString.name("description"),
    )
)
