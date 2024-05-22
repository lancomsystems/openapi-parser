package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val tagGoupsExtensionEntry = OpenApiEntity(
    entityType = EntityType.TagGroupsExtensionEntry,
    extensions = false,
    fields = listOf(
        fieldString.name("name"),
        fieldString.list().name("tags"),
    )
)
