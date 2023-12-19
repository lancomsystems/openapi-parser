package de.lancom.openapi.codegen.type

import de.lancom.openapi.codegen.field.Field

sealed class Type {
    abstract val required: Boolean
    abstract val type: String
    open val imports: Set<String> = emptySet()

    val opt: String
        get() = if (required) {
            ""
        } else {
            "?"
        }

    val typeOpt: String
        get() = type + opt

    abstract fun required(): Type

    operator fun invoke(): Field {
        return Field(this)
    }
}
