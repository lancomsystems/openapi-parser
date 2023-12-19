package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType

val components = OpenApiEntity(
    entityType = EntityType.Components,
    fields = listOf(
        fieldSchemaOrRef.map().name("schemas"),
        fieldResponseOrRef.map().name("responses"),
        fieldParameterOrRef.map().name("parameters"),
        fieldExampleOrRef.map().name("examples"),
        fieldRequestBodyOrRef.map().name("requestBodies"),
        fieldHeaderOrRef.map().name("headers"),
        fieldSecuritySchemeOrRef.map().name("securitySchemes"),
        fieldLinkOrRef.map().name("links"),
        fieldCallbackOrRef.map().name("callbacks"),
    )
)
