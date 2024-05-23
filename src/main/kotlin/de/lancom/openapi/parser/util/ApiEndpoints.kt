package de.lancom.openapi.parser.util

import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.entity.Operation
import de.lancom.openapi.parser.entity.Paths

fun OpenApi.apiEndpoints(): Map<String, Map<OperationMethod, Operation>> {
    return paths?.apiEndpoints() ?: emptyMap()
}

fun Paths.apiEndpoints(): Map<String, Map<OperationMethod, Operation>> {
    return pathItems.mapNotNull { (path, pathItem) ->
        pathItem?.operations?.let { path to it }
    }.toMap()
}
