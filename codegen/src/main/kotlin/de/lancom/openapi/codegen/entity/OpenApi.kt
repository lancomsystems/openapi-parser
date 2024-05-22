package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val openApi = OpenApiEntity(
    entityType = EntityType.OpenApi,
    fields = listOf(
        fieldString.name("openapi"),
        fieldInfo.name("info"),
        fieldExternalDocumentation.name("externalDocs"),
        fieldServerList.name("servers"),
        fieldSecurityRequirementList.name("security"),
        fieldTagList.name("tags"),
        fieldPaths.name("paths"),
        fieldComponents.name("components"),
    )
)
