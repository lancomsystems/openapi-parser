package de.lancom.openapi.parser.util

import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.common.util.takeUnlessEmpty
import de.lancom.openapi.parser.entity.*
import de.lancom.openapi.parser.field.Field

fun OpenApi.filterApiEndpoints(
    filter: (String, OperationMethod, Operation?) -> Boolean,
): OpenApi {
    return paths?.filterApiEndpoints(filter)?.let { paths ->
        setPaths(paths)
    } ?: this
}

fun OpenApi.filterApiEndpoints(
    path: String,
    operationType: OperationMethod,
): OpenApi {
    return filterApiEndpoints { filterPath, filterOperationType, _ ->
        path == filterPath && operationType == filterOperationType
    }
}

fun Paths.filterApiEndpoints(
    filter: (String, OperationMethod, Operation?) -> Boolean,
): Paths {
    return copy(
        _pathItems = _pathItems.flatMap { pathItems: Map<String, PathItem?>? ->
            Field.unsetIfNull(
                pathItems?.filterApiEndpoints(filter)
            )
        }
    ).updateFields()
}

fun Map<String, PathItem?>.filterApiEndpoints(
    filter: (String, OperationMethod, Operation?) -> Boolean,
): Map<String, PathItem>? {
    return mapNotNull { (path, pathItem) ->
        pathItem?.filterApiEndpoints { operationType ->
            filter(path, operationType, pathItem.getOperation(operationType))
        }?.let { item ->
            path to item
        }
    }.toMap().takeUnlessEmpty()
}

fun PathItem.filterApiEndpoints(
    filter: (OperationMethod) -> Boolean,
): PathItem? {
    return OperationMethod.entries.fold(this) { acc, operationMethod ->
        if (filter(operationMethod)) {
            acc
        } else {
            acc.unsetOperation(operationMethod)
        }
    }.takeIf(PathItem::hasOperations)
}
