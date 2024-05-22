package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Tag(
    val name: String? = null,
    val description: String? = null,
    val externalDocs: JsonNode? = null,
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions
