package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldPathItem
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val callback = OpenApiEntity(
    entityType = EntityType.Callback,
    fields = listOf(
        fieldPathItem.map().flat().defaultEmptyMap().unsetIfEmpty().name("pathItems"),
    )
)
