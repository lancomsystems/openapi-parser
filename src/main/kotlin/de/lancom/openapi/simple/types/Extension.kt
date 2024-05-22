package de.lancom.openapi.simple.types

data class Extension(val value: Any? = null)

fun Map<String, Extension>.unpackExtensions(): Map<String, Any?> {
    return mapValues { (_, extension) ->
        extension.value
    }
}
