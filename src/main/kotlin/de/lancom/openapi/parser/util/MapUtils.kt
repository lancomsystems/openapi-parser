package de.lancom.openapi.parser.util

inline fun <K, In, Out> Map<K, In>.mapValueNotNull(transform: (In) -> Out?): Map<K, Out> {
    return mapNotNull { (key, value) ->
        val transformed = transform(value)
        if (transformed != null) {
            key to transformed
        } else {
            null
        }
    }.toMap()
}

inline fun <In, Out, V> Map<In, V>.mapKeyNotNull(transform: (In) -> Out?): Map<Out, V> {
    return mapNotNull { (key, value) ->
        val transformed = transform(key)
        if (transformed != null) {
            transformed to value
        } else {
            null
        }
    }.toMap()
}
