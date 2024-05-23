package de.lancom.openapi.common.util

fun <C : Collection<C>> C?.takeUnlessEmpty(): C? {
    return if (isNullOrEmpty()) {
        null
    } else {
        this
    }
}

fun <K, V> Map<K, V>?.takeUnlessEmpty(): Map<K, V>? {
    return if (isNullOrEmpty()) {
        null
    } else {
        this
    }
}
