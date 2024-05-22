package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension

data class ServerVariable(
    val enum: List<String> = emptyList(),
    val default: String? = null,
    val description: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
)
