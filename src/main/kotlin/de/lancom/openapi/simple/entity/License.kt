package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class License(
    val name: String? = null,
    val url: String? = null,
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addName(value: String): License {
        return copy(name = value)
    }

    fun addUrl(value: String): License {
        return copy(url = value)
    }
}
