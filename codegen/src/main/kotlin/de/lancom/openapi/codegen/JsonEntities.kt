package de.lancom.openapi.codegen

import de.lancom.openapi.codegen.field.fieldRawJson
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val jsonEntities = listOf(
    EntityType.DefaultJson,
    EntityType.Discriminator,
    EntityType.ExampleJson,
    EntityType.ExternalDocumentation,
    EntityType.RequestBodyJson,
    EntityType.SecurityRequirement,
    EntityType.XML,
).map { entityType ->
    OpenApiEntity(
        entityType = entityType,
        extensions = false,
        fields = listOf(
            fieldRawJson.name("jsonNode"),
        )
    )
}
