package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldPathItem
import de.lancom.openapi.codegen.type.EntityType

val callback = OpenApiEntity(
    entityType = EntityType.Callback,
    fields = listOf(
        fieldPathItem.map().flat().defaultEmptyMap().unsetIfEmpty().name("pathItems"),
    )
)
