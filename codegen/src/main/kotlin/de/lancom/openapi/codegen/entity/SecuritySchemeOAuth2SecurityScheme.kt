package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldSecuritySchemeType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

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
