package de.lancom.openapi.simple.util

import de.lancom.openapi.simple.types.Extension

interface Extensions {
    val extensions: Map<String, Extension>
}
