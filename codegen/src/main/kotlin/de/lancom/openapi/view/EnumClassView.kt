package de.lancom.openapi.view

import de.lancom.openapi.codegen.type.EnumType

class EnumClassView(
    enum: EnumType,
) {
    val enumClassName: String = enum.enum
    val values: List<NameView> = enum.values.map(::NameView)
}
