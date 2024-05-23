package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val header = OpenApiEntity(
    entityType = EntityType.Header,
    fields = listOf(
        fieldString.name("description"),
        fieldBooleanRequiredDefaultFalse.name("required"),
        fieldBooleanRequiredDefaultFalse.name("deprecated"),
        fieldBooleanRequiredDefaultFalse.name("allowEmptyValue"),
        fieldHeaderStyle.default("HeaderStyle.Simple").name("style"),
        fieldBoolean.name("explode"),
        fieldBooleanRequiredDefaultFalse.name("allowReserved"),
        fieldSchemaOrRef.name("schema"),
        fieldMediaType.map().name("content"),
        fieldExampleJson.name("example"),
        fieldExampleOrRef.map().name("examples"),
    )
)
