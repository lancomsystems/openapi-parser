package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Info(
    val title: String? = null,
    val description: String? = null,
    val termsOfService: String? = null,
    val contact: Contact? = null,
    val license: License? = null,
    val version: String? = null,
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addContact(contact: Contact): Info {
        return copy(contact = contact)
    }

    fun addDescription(description: String): Info {
        return copy(description = description)
    }

    fun addLicense(license: License): Info {
        return copy(license = license)
    }

    fun addTermsOfService(termsOfService: String): Info {
        return copy(termsOfService = termsOfService)
    }

    fun addTitle(title: String): Info {
        return copy(title = title)
    }

    fun addVersion(version: String): Info {
        return copy(version = version)
    }
}
