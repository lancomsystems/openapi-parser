package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType

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
