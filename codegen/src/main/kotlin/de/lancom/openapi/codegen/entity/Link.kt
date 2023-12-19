package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldRequestBodyJson
import de.lancom.openapi.codegen.field.fieldServer
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val link = OpenApiEntity(
    entityType = EntityType.Link,
    fields = listOf(
        fieldString.name("operationId"),
        fieldString.name("operationRef"),
        fieldString.map().name("parameters"),
        fieldRequestBodyJson.name("requestBody"),
        fieldString.name("description"),
        fieldServer.name("server"),
    )
)
