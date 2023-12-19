package de.lancom.openapi.view

import de.lancom.openapi.codegen.field.fieldRawJson
import de.lancom.openapi.codegen.type.MapType

class EntityDescriptorView(
    private val fields: List<FieldView>,
) {
    val jsonNode: String = fields.jsonNode?.name?.underscore ?: "null"

    val map: List<FieldView>
        get() = fields.filter { fieldView ->
            !fieldView.field.flat
        }

    val flatMap: List<FieldView>
        get() = fields.filter { fieldView ->
            fieldView.field.flat && fieldView.field != fieldRawJson && fieldView.field.type is MapType
        }

    val flatten: List<FieldView>
        get() = fields.filter { fieldView ->
            fieldView.field.flat && fieldView.field != fieldRawJson && fieldView.field.type !is MapType
        }
}
