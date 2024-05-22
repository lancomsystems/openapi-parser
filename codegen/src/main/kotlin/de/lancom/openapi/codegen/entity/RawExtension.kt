package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldRawJson
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val rawExtension = OpenApiEntity(
    entityType = EntityType.RawExtension,
    baseType = "Extension",
    extensions = false,
    fields = listOf(
        fieldRawJson.name("jsonNode"),
    )
)