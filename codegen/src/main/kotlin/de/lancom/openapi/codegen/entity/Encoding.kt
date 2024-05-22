package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val encoding = OpenApiEntity(
    entityType = EntityType.Encoding,
    fields = listOf(
        fieldString.name("contentType"),
        fieldHeaderOrRef.map().name("headers"),
        fieldStyle.name("style"),
        fieldBoolean.name("explode"),
        fieldBooleanRequiredDefaultFalse.name("allowReserved"),
    )
)
