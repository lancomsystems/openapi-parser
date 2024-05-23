package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldExampleJson
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val example = OpenApiEntity(
    entityType = EntityType.Example,
    fields = listOf(
        fieldString.name("summary"),
        fieldString.name("description"),
        fieldExampleJson.name("value"),
        fieldString.name("externalValue"),
    )
)
