package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.field.fieldStringList
import de.lancom.openapi.codegen.type.EntityType.Companion.ServerVariable
import de.lancom.openapi.codegen.view.OpenApiEntity

val serverVariable = OpenApiEntity(
    entityType = ServerVariable,
    fields = listOf(
        fieldStringList.name("enum"),
        fieldString.name("default"),
        fieldString.name("description"),
    )
)
