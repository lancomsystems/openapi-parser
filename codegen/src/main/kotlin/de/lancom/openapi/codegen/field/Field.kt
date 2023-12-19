package de.lancom.openapi.codegen.field

import de.lancom.openapi.codegen.type.*
import de.lancom.openapi.view.FieldView
import de.lancom.openapi.view.NameView

data class Field(
    val type: Type,
    val flat: Boolean = false,
    val regex: Boolean = false,
    val unsetIfEmpty: Boolean = false,
    val default: String? = null,
    val fixed: String? = null,
    val options: List<String> = emptyList(),
) {
    fun flat(): Field {
        return copy(flat = true)
    }

    fun regex(): Field {
        return copy(regex = true)
    }

    fun unsetIfEmpty(): Field {
        return copy(unsetIfEmpty = true)
    }

    fun default(default: String): Field {
        return copy(default = default, type = type.required())
    }

    fun default0(): Field {
        return default("0")
    }

    fun defaultFalse(): Field {
        return default("false")
    }

    fun defaultTrue(): Field {
        return default("true")
    }

    fun defaultEmptyMap(): Field {
        return default("emptyMap()")
    }

    fun defaultEmptyList(): Field {
        return default("emptyList()")
    }

    fun fixed(fixed: String): Field {
        return copy(fixed = fixed)
    }

    fun name(name: String): FieldView {
        return FieldView(NameView(name), this)
    }

    fun options(vararg option: String): Field {
        return copy(options = this.options + option)
    }

    fun reference(): Field {
        type as EntityType
        return copy(type = ReferenceType(type.required()))
    }

    fun referenceOrInstance(): Field {
        type as EntityType
        return copy(type = ReferenceOrInstance(type.required()))
    }

    fun list(): Field {
        return copy(type = ListType(type))
    }

    fun map(key: Type = SimpleType.string.required()): Field {
        return copy(type = MapType(key, type))
    }

    fun set(): Field {
        return copy(type = SetType(type))
    }

    fun required(): Field {
        return copy(type = type.required())
    }
}
