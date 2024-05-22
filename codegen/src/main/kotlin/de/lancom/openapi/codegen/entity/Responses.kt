package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.fieldResponseOrRef
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.type.SimpleType
import de.lancom.openapi.codegen.view.OpenApiEntity

val responses = OpenApiEntity(
    entityType = EntityType.Responses,
    fields = listOf(
        fieldResponseOrRef.map(key = SimpleType.responseStatusCode.required())
            .unsetIfEmpty().defaultEmptyMap().flat().name("responses"),
    )
)
