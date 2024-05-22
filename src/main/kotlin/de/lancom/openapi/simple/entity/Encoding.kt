package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.Style
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Encoding(
    val contentType: String? = null,
    val headers: Map<String, HeaderOrRef> = emptyMap(),
    val style: Style? = null,
    val explode: Boolean? = null,
    val allowReserved: Boolean = false,
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions
