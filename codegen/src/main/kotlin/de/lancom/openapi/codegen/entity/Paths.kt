package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldPathItem
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val paths = OpenApiEntity(
    entityType = EntityType.Paths,
    fields = listOf(
        fieldPathItem.map().defaultEmptyMap().flat().name("pathItems"),
    )
)
