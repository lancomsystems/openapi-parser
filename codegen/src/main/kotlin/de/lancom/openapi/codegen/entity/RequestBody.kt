package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldBooleanRequiredDefaultFalse
import de.lancom.openapi.codegen.field.fieldMediaType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.view.OpenApiEntity

val requestBody = OpenApiEntity(
    entityType = EntityType.RequestBody,
    fields = listOf(
        fieldString.name("description"),
        fieldMediaType.map().name("content"),
        fieldBooleanRequiredDefaultFalse.name("required"),
    )
)
