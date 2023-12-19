package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType

val header = OpenApiEntity(
    entityType = EntityType.Header,
    fields = listOf(
        fieldString.name("description"),
        fieldBooleanRequiredDefaultFalse.name("required"),
        fieldBooleanRequiredDefaultFalse.name("deprecated"),
        fieldBooleanRequiredDefaultFalse.name("allowEmptyValue"),
        fieldHeaderStyle.default("HeaderStyle.simple").name("style"),
        fieldBoolean.name("explode"),
        fieldBooleanRequiredDefaultFalse.name("allowReserved"),
        fieldSchemaOrRef.name("schema"),
        fieldMediaType.map().name("content"),
        fieldExampleJson.name("example"),
        fieldExampleOrRef.map().name("examples"),
    )
)
