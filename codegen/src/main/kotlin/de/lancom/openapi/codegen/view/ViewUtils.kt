package de.lancom.openapi.codegen.view

import de.lancom.openapi.codegen.type.SimpleType

val List<FieldView>.jsonNode: FieldView?
    get() = find { fieldView ->
        fieldView.field.type.type == SimpleType.jsonNode.type
    }
