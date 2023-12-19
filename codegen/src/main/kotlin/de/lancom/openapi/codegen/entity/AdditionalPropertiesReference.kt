package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldSchemaRef
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.view.OpenApiEntity

val additionalPropertiesReference = OpenApiEntity(
    entityType = EntityType.AdditionalProperties,
    name = "AdditionalPropertiesReference",
    baseType = "AdditionalProperties",
    companion = false,
    extensions = false,
    emptyYaml = "\\\$ref: '#/components/schemas/Example'",
    emptySubject = "de.lancom.openapi.entity.AdditionalProperties(\"#/components/schemas/Example\")",
    fields = listOf(
        fieldSchemaRef.flat().name("reference"),
    )
)
