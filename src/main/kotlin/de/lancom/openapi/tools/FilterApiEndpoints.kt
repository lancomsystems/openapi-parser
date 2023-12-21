package de.lancom.openapi.tools

import de.lancom.openapi.entity.*
import de.lancom.openapi.field.Field
import de.lancom.openapi.jackson.takeUnlessEmpty

fun OpenApi.filterApiEndpoints(
    filter: (String, OperationType, Operation?) -> Boolean,
): OpenApi {
    return paths?.filterApiEndpoints(filter)?.let { paths ->
        setPaths(paths)
    } ?: this
}

fun OpenApi.filterApiEndpoints(
    path: String,
    operationType: OperationType,
): OpenApi {
    return filterApiEndpoints { filterPath, filterOperationType, _ ->
        path == filterPath && operationType == filterOperationType
    }
}

fun Paths.filterApiEndpoints(
    filter: (String, OperationType, Operation?) -> Boolean,
): Paths {
    return copy(
        _pathItems = _pathItems.flatMap { pathItems: Map<String, PathItem?>? ->
            Field.unsetIfNull(
                pathItems?.filterApiEndpoints(filter)
            )
        }
    )
}

fun Map<String, PathItem?>.filterApiEndpoints(
    filter: (String, OperationType, Operation?) -> Boolean,
): Map<String, PathItem>? {
    return mapNotNull { (path, pathItem) ->
        pathItem?.filterApiEndpoints { operationType ->
            filter(path, operationType, operationType[pathItem])
        }?.let { item ->
            path to item
        }
    }.toMap().takeUnlessEmpty()
}

fun PathItem.filterApiEndpoints(
    filter: (OperationType) -> Boolean,
): PathItem? {
    return OperationType.set(this, OperationType(this).filterKeys(filter))
        .takeIf(OperationType::hasOperations)
}
