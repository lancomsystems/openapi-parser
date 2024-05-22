package de.lancom.openapi.simple.util

import de.lancom.openapi.common.types.Path
import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.simple.entity.OpenApi
import de.lancom.openapi.simple.entity.PathItem
import de.lancom.openapi.simple.entity.Paths

fun OpenApi.filterApiEndpointsStartsWith(
    path: Path,
): OpenApi {
    return filterApiEndpoints { operationInfo: OperationInfo ->
        operationInfo.path.path.startsWith(path.path)
    }
}

fun OpenApi.filterApiEndpoints(
    path: Path,
    operationMethod: OperationMethod,
): OpenApi {
    return filterApiEndpoints { operationInfo: OperationInfo ->
        operationInfo.path == path && operationInfo.operationMethod == operationMethod
    }
}

fun OpenApi.filterApiEndpoints(
    filter: (OperationInfo) -> Boolean,
): OpenApi {
    return copy(
        paths = paths.filterApiEndpoints(filter),
    )
}

fun Paths.filterApiEndpoints(
    filter: (OperationInfo) -> Boolean,
): Paths {
    return copy(
        pathItems = pathItems.mapNotNull { (path, pathItem) ->
            val filtered = pathItem.filterApiEndpoints(path, filter)
            if (filtered.operations.isEmpty()) {
                null
            } else {
                path to filtered
            }
        }.toMap()
    )
}

fun PathItem.filterApiEndpoints(
    path: Path,
    filter: (OperationInfo) -> Boolean,
): PathItem {
    return operations.toList().fold(this) { left, (operationMethod, operation) ->
        val operationInfo = OperationInfo(
            path = path,
            pathItem = this,
            operation = operation,
            operationMethod = operationMethod,
        )

        if (filter(operationInfo)) {
            left
        } else {
            left.setOperation(operationMethod, null)
        }
    }
}
