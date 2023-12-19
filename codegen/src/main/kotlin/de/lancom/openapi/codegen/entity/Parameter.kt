package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType

val parameter = OpenApiEntity(
    entityType = EntityType.Parameter,
    fields = listOf(
        fieldString.name("name"),
        fieldIn.name("in"),
        fieldString.name("description"),
        fieldBooleanRequiredDefaultFalse.name("required"),
        fieldBooleanRequiredDefaultFalse.name("deprecated"),
        fieldBooleanRequiredDefaultFalse.name("allowEmptyValue"),
        fieldString.options(
            "matrix",
            "label",
            "form",
            "simple",
            "spaceDelimited",
            "pipeDelimited",
            "deepObject",
        ).name("style"),
        fieldBoolean.name("explode"),
        fieldBooleanRequiredDefaultFalse.name("allowReserved"),
        fieldSchemaOrRef.name("schema"),
        fieldMediaType.map().name("content"),
        fieldExampleJson.name("example"),
        fieldExampleOrRef.map().name("examples"),
    )
)
