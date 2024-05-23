package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val tagGoupsExtension = OpenApiEntity(
    entityType = EntityType.TagGroupsExtension,
    baseType = "ListExtension",
    extensions = false,
    emptyYaml = "[]",
    unsetField = "Field(emptyList())",
    fields = listOf(
        EntityType.TagGroupsExtensionEntry().list().flat().required().defaultEmptyList().name("tags"),
    )
)
