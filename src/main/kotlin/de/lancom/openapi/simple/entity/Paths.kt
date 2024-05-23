package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.Path
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Paths(
    val pathItems: Map<Path, PathItem> = emptyMap(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addPathItem(path: Path, pathItem: PathItem): Paths {
        return copy(pathItems = pathItems + (path to pathItem))
    }
}
