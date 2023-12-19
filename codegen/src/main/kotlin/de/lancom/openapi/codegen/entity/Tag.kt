package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldExternalDocumentation
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val tag = OpenApiEntity(
    entityType = EntityType.Tag,
    fields = listOf(
        fieldString.name("name"),
        fieldString.name("description"),
        fieldExternalDocumentation.name("externalDocs"),
    )
)
