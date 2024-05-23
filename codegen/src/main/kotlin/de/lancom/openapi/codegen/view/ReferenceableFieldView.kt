package de.lancom.openapi.codegen.view

import de.lancom.openapi.codegen.type.EntityType

data class ReferenceableFieldView(
    val fieldView: FieldView,
    val itemType: EntityType,
)
