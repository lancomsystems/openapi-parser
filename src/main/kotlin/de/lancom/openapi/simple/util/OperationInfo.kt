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
        }.sortedBy(OperationInfo::operationId)
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
    val (left, right) = path.split("?", limit = 2) + listOf("", "")

    val prefix = left.split("/")
        .joinToString("") { str ->
            if (str.startsWith("{") && str.endsWith("}")) {
                str
                    .drop(1)
                    .dropLast(1)
                    .replaceFirstChar(Char::uppercaseChar)
            } else {
                str
                    .split(" ", "-", ".", "_", "=")
                    .joinToString("") {
                        if (it.all(Char::isUpperCase)) {
                            it.lowercase().replaceFirstChar(Char::uppercaseChar)
                        } else {
                            it.replaceFirstChar(Char::uppercaseChar)
                        }
                    }
                    .replaceFirstChar(Char::uppercaseChar)
            }
        }
        .replaceFirstChar(Char::lowercaseChar)

    val query = right
        .replace("=", "")
        .replace("&", "")

    val suffix = method.name.replaceFirstChar(Char::uppercaseChar)

    return prefix + query + suffix
}
