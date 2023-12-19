package de.lancom.openapi.codegen.entity

import de.lancom.openapi.view.OpenApiEntity
import de.lancom.openapi.codegen.field.fieldContact
import de.lancom.openapi.codegen.field.fieldLicense
import de.lancom.openapi.codegen.field.fieldString
import de.lancom.openapi.codegen.type.EntityType

val info = OpenApiEntity(
    entityType = EntityType.Info,
    fields = listOf(
        fieldString.name("title"),
        fieldString.name("description"),
        fieldString.name("termsOfService"),
        fieldContact.name("contact"),
        fieldLicense.name("license"),
        fieldString.name("version"),
    )
)
