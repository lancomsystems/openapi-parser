package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldServerVariable
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val server = OpenApiEntity(
    entityType = EntityType.Server,
    fields = listOf(
        fieldString.name("url"),
        fieldString.name("description"),
        fieldServerVariable.map().name("variables"),
    )
)
