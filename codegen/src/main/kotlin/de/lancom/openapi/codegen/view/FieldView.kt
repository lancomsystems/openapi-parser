package de.lancom.openapi.codegen.view

import de.lancom.openapi.codegen.field.Field
import de.lancom.openapi.codegen.field.fieldRawJson
import de.lancom.openapi.codegen.type.*

data class FieldView(
    val name: NameView,
    val field: Field,
) {
    val requiredAndNoDefault: Boolean = field.type.required && field.default == null
    val addOnlyToNull: Boolean = field.type.required || field.default != null

    val override: Boolean = field.override

    val jsonNode: Boolean = field == fieldRawJson
    val flat: Boolean = field.flat

    private val opt: String = if (addOnlyToNull) {
        ""
    } else {
        "?"
    }

    val type: String = field.type.type

    val typeOpt: String = "$type$opt"

    val default: String = if (field.default == null) {
        ""
    } else {
        " ?: ${field.default}"
    }

    val referenceable: ReferenceableFieldView? =
        if (field.type is ReferenceOrInstance && field.type.itemType.referenceable) {
            ReferenceableFieldView(this, field.type.itemType)
        } else if (field.type is ListType && field.type.itemType is ReferenceOrInstance && field.type.itemType.itemType.referenceable) {
            ReferenceableFieldView(this, field.type.itemType.itemType)
        } else if (field.type is SetType && field.type.itemType is ReferenceOrInstance && field.type.itemType.itemType.referenceable) {
            ReferenceableFieldView(this, field.type.itemType.itemType)
        } else if (field.type is MapType && field.type.value is ReferenceOrInstance && field.type.value.itemType.referenceable) {
            ReferenceableFieldView(this, field.type.value.itemType)
        } else {
            null
        }

    val invertedDefault: String? =
        if (field.type is SimpleType && field.type.type == SimpleType.boolean.type) {
            when (field.default) {
                "true" ->
                    " = false"

                "false" ->
                    " = true"

                else ->
                    null
            }
        } else {
            null
        }

    val regex: Boolean = field.regex

    val collectionType: String? = (field.type as? CollectionType)?.typeName

    val enumValues: List<NameView>? = (field.type as? EnumType)?.values?.map(::NameView)

    val optionValues: List<NameView> = field.options.map(::NameView)

    val listField: Boolean = field.type is ListType

    val setField: Boolean = field.type is SetType

    val mapField: Boolean = field.type is MapType

    val entityField: Boolean = field.type is EntityType

    val otherField: Boolean = !(listField || setField || mapField || entityField)

    val otherOrEntityField: Boolean = otherField || entityField

    val get0: String = when {
        flat && listField ->
            ".getList"

        flat ->
            ".getUnlessEmpty"

        field.type.required ->
            "[\"${name.lowercase}\"].getSingle"

        else ->
            "[\"${name.lowercase}\"].getNullOrElse"
    }

    val get1: String = when {
        field.type is MapType && field.type.key.type == SimpleType.string.type ->
            "getMap"

        field.type is MapType ->
            "getMap(::${field.type.key.type})"

        setField ->
            "getSet"

        !flat && listField ->
            "getList"

        else ->
            "getSingle"
    }

    val get2: String = when {
        field.type is CollectionType && !field.type.itemType.required ->
            "getNullOrElse"

        field.type is MapType && !field.type.value.required ->
            "getNullOrElse"

        else ->
            "getSingle"
    }

    val get3: String = get3(field.type)
}

private fun get3(type: Type): String {
    return when {
        type is MapType ->
            get3(type.value)

        type is ListType ->
            get3(type.itemType)

        type is SetType ->
            get3(type.itemType)

        type is EntityType && type.required ->
            "getSingle(${type.type}::parseEntity)"

        type is EntityType ->
            "getSingle(${type.type}::parseEntityOpt)"

        type is ReferenceOrInstance ->
            "getSingle(${type.itemType.type}::parseReferenceOrEntityOpt)"

        type is ReferenceType ->
            "getSingle(${type.itemType.type}::parseReferenceOrEntityOpt)"

        type is EnumType ->
            "getEnum(${type.type}::fromString)"

        type.type == "Boolean" ->
            "getBoolean()"

        type.type == "String" ->
            "getString()"

        type.type == "Int" ->
            "getInt()"

        type.type == "Double" ->
            "getDouble()"

        type is SimpleType ->
            "TODO simple(${type.type}::valueOf)"

        else ->
            "TODO(\"$type\")"
    }
}
