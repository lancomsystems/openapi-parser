package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val contact = OpenApiEntity(
    entityType = EntityType.Contact,
    fields = listOf(
        fieldString.name("name"),
        fieldString.name("url"),
        fieldString.name("email"),
    )
)
