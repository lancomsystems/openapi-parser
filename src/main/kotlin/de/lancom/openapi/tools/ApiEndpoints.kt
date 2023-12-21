package de.lancom.openapi.tools

import de.lancom.openapi.entity.OpenApi
import de.lancom.openapi.entity.Operation
import de.lancom.openapi.entity.OperationType
import de.lancom.openapi.entity.Paths
import de.lancom.openapi.jackson.takeUnlessEmpty

fun OpenApi.apiEndpoints(): Map<String, Map<OperationType, Operation>> {
    return paths?.apiEndpoints() ?: emptyMap()
}

fun Paths.apiEndpoints(): Map<String, Map<OperationType, Operation>> {
    return pathItems.mapNotNull { (path, pathItem) ->
        if (pathItem == null) {
            null
        } else {
            OperationType(pathItem).takeUnlessEmpty()?.let { operations ->
                path to operations
            }
        }
    }.toMap()
}
