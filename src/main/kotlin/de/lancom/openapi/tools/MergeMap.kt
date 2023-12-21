package de.lancom.openapi.tools

import de.lancom.openapi.entity.Entity

fun <K, V> mergeMap(left: Map<K, V>, right: Map<K, V>): Map<K, V> {
    val allKeys = left.keys + right.keys

    return allKeys.associateWith { key ->
        val l = left[key]
        val r = right[key]

        when {
            l != null && r != null ->
                if (l is Entity && r is Entity) {
                    @Suppress("UNCHECKED_CAST")
                    l.mergeEntity(r) as V
                } else {
                    r
                }

            l != null ->
                l

            r != null ->
                r

            else ->
                TODO("Both values should not be null")
        }
    }
}
