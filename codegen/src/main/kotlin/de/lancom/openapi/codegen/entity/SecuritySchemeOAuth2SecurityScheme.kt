package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldSecuritySchemeType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val securitySchemeOAuth2SecurityScheme = OpenApiEntity(
    entityType = EntityType.OAuth2SecurityScheme,
    baseType = "SecurityScheme",
    emptyYaml = "type: oauth2",
    fields = listOf(
        fieldSecuritySchemeType.fixed("SecuritySchemeType.oauth2").name("type"),
        //"flows"
        fieldString.name("description"),
    )
)
