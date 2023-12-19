package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldPathItem
import de.lancom.openapi.codegen.type.EntityType

val paths = OpenApiEntity(
    entityType = EntityType.Paths,
    fields = listOf(
        fieldPathItem.map().defaultEmptyMap().flat().name("pathItems"),
    )
)
