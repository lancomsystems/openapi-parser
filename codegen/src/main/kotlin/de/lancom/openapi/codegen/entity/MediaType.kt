package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldEncoding
import de.lancom.openapi.codegen.field.fieldExampleOrRef
import de.lancom.openapi.codegen.field.fieldExampleJson
import de.lancom.openapi.codegen.field.fieldSchemaOrRef
import de.lancom.openapi.codegen.type.EntityType

val mediaType = OpenApiEntity(
    entityType = EntityType.MediaType,
    fields = listOf(
        fieldSchemaOrRef.name("schema"),
        fieldExampleJson.name("example"),
        fieldExampleOrRef.map().name("examples"),
        fieldEncoding.map().name("encoding"),
    )
)
