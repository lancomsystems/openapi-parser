package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldSchema
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val additionalPropertiesSchema = OpenApiEntity(
    entityType = EntityType.AdditionalProperties,
    name = "AdditionalPropertiesSchema",
    baseType = "AdditionalProperties",
    companion = false,
    extensions = false,
    emptySubject = "de.lancom.openapi.parser.entity.AdditionalProperties(de.lancom.openapi.parser.entity.Schema())",
    fields = listOf(
        fieldSchema.flat().name("schema"),
    )
)
