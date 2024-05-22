package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldHeader
import de.lancom.openapi.codegen.field.fieldLinkOrRef
import de.lancom.openapi.codegen.field.fieldMediaType
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val response = OpenApiEntity(
    entityType = EntityType.Response,
    fields = listOf(
        fieldString.name("description"),
        fieldHeader.map().name("headers"),
        fieldMediaType.map().name("content"),
        fieldLinkOrRef.map().name("links"),
    )
)
