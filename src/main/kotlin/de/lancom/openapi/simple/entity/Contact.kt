package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Contact(
    val name: String? = null,
    val url: String? = null,
    val email: String? = null,
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addEmail(email: String): Contact {
        return copy(email = email)
    }

    fun addName(name: String): Contact {
        return copy(name = name)
    }

    fun addUrl(url: String): Contact {
        return copy(url = url)
    }
}
