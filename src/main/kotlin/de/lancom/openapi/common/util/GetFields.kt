package de.lancom.openapi.common.util

import de.lancom.openapi.parser.field.Field

fun <K, V> Field<Map<K, V>>.getFields(): Set<String>? {
    return orNull
        ?.keys
        ?.map { key ->
            key.toString()
        }
        ?.toSet()
}
