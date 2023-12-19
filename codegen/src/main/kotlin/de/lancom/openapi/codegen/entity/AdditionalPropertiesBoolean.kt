package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.view.OpenApiEntity

val additionalPropertiesBoolean = OpenApiEntity(
    entityType = EntityType.AdditionalProperties,
    name = "AdditionalPropertiesBoolean",
    baseType = "AdditionalProperties",
    companion = false,
    extensions = false,
    emptyYaml = "true",
    emptySubject = "AdditionalPropertiesBoolean",
    fields = listOf(
    )
)
