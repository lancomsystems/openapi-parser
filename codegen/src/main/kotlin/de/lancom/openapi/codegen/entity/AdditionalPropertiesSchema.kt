package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldSchema
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.view.OpenApiEntity

val additionalPropertiesSchema = OpenApiEntity(
    entityType = EntityType.AdditionalProperties,
    name = "AdditionalPropertiesSchema",
    baseType = "AdditionalProperties",
    companion = false,
    extensions = false,
    emptySubject = "de.lancom.openapi.entity.AdditionalProperties(de.lancom.openapi.entity.Schema())",
    fields = listOf(
        fieldSchema.flat().name("schema"),
    )
)
