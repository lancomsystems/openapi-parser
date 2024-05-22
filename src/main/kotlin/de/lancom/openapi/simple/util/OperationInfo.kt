package de.lancom.openapi.simple.util

import de.lancom.openapi.common.types.Path
import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.simple.entity.OpenApi
import de.lancom.openapi.simple.entity.Operation
import de.lancom.openapi.simple.entity.PathItem

data class OperationInfo(
    val path: Path,
    val pathItem: PathItem,
    val operation: Operation,
    val operationMethod: OperationMethod,
) {
    val operationId: String = operation.operationId ?: path.toOperationId(operationMethod)

    companion object {
        operator fun invoke(path: Path, pathItem: PathItem): List<OperationInfo> {
            return pathItem.operations.map { (method, operation) ->
                OperationInfo(path, pathItem, operation, method)
            }.sortedBy(OperationInfo::operationId)
        }
    }
}

val OpenApi.operations: List<OperationInfo>
    get() {
        return paths.pathItems.flatMap { (path, pathItem) ->
            OperationInfo(path, pathItem)
        }
    }

val PathItem.operations: Map<OperationMethod, Operation>
    get() = mapOf(
        OperationMethod.Get to get,
        OperationMethod.Put to put,
        OperationMethod.Post to post,
        OperationMethod.Delete to delete,
        OperationMethod.Options to options,
        OperationMethod.Head to head,
        OperationMethod.Patch to patch,
        OperationMethod.Trace to trace,
    ).mapNotNull { (type, operation) ->
        if (operation == null) {
            null
        } else {
            type to operation
        }
    }.toMap()

fun Path.toOperationId(method: OperationMethod): String {
    return Regex("/[a-z]").replace(
        path.drop(1)
            .replace("{", "")
            .replace("}", "")
    ) {
        it.value.drop(1).uppercase()
    } + method.name.replaceFirstChar(Char::uppercaseChar)
}
