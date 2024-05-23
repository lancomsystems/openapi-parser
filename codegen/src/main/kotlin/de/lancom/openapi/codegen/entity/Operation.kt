package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val operation = OpenApiEntity(
    entityType = EntityType.Operation,
    fields = listOf(
        fieldStringList.name("tags"),
        fieldString.name("summary"),
        fieldString.name("description"),
        fieldExternalDocumentation.name("externalDocs"),
        fieldString.name("operationId"),
        fieldParameterOrRef.list().name("parameters"),
        fieldRequestBodyOrRef.name("requestBody"),
        fieldResponses.name("responses"),
        fieldCallbackOrRef.map().name("callbacks"),
        fieldBooleanRequiredDefaultFalse.name("deprecated"),
        fieldSecurityRequirementList.name("security"),
        fieldServerList.name("servers"),
    )
)
