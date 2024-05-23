package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldOperation
import de.lancom.openapi.codegen.field.fieldParameterOrRef
import de.lancom.openapi.codegen.field.fieldServerList
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val pathItem = OpenApiEntity(
    entityType = EntityType.PathItem,
    fields = listOf(
        fieldString.name("summary"),
        fieldString.name("description"),
        fieldServerList.name("servers"),
        fieldParameterOrRef.list().name("parameters"),
        fieldOperation.name("get"),
        fieldOperation.name("put"),
        fieldOperation.name("post"),
        fieldOperation.name("delete"),
        fieldOperation.name("options"),
        fieldOperation.name("head"),
        fieldOperation.name("patch"),
        fieldOperation.name("trace"),
    )
)
