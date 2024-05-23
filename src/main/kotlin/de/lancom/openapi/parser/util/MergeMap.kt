package de.lancom.openapi.parser.util

import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Referenceable

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
                } else if (l is Instance<*> && r is Instance<*>) {
                    @Suppress("UNCHECKED_CAST")
                    Instance(l.referenced.mergeEntity(r.referenced) as Referenceable) as V
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
