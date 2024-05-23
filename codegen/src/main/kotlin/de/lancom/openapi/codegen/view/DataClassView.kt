package de.lancom.openapi.codegen.view

import de.lancom.openapi.codegen.type.EntityType

class DataClassView(
    val imports: List<String>,
    val entityName: String,
    val `interface`: String,
    val jsonEntity: Boolean,
    val entityType: EntityType,
    val baseType: String?,
    val companionType: String?,
    val fields: List<FieldView>,
    val companion: DataClassCompanionView?,
    val unsetField: String,
) {
    val entityDescriptor = EntityDescriptorView(fields)

    val baseTypeOrEntityName: String = baseType ?: entityName

    val companionOrEntityName: String = companionType ?: entityName

    val dataClass: Boolean = fields.isNotEmpty()
}
