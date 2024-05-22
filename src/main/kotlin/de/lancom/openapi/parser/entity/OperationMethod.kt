package de.lancom.openapi.parser.entity

import de.lancom.openapi.common.util.OperationMethod

fun PathItem.setOperation(
    operationMethod: OperationMethod,
    operation: Operation?,
): PathItem {
    return when (operationMethod) {
        OperationMethod.Get -> setGet(operation)
        OperationMethod.Put -> setPut(operation)
        OperationMethod.Post -> setPost(operation)
        OperationMethod.Delete -> setDelete(operation)
        OperationMethod.Options -> setOptions(operation)
        OperationMethod.Head -> setHead(operation)
        OperationMethod.Patch -> setPatch(operation)
        OperationMethod.Trace -> setTrace(operation)
    }
}

fun PathItem.unsetOperation(
    operationMethod: OperationMethod,
): PathItem {
    return when (operationMethod) {
        OperationMethod.Get -> unsetGet()
        OperationMethod.Put -> unsetPut()
        OperationMethod.Post -> unsetPost()
        OperationMethod.Delete -> unsetDelete()
        OperationMethod.Options -> unsetOptions()
        OperationMethod.Head -> unsetHead()
        OperationMethod.Patch -> unsetPatch()
        OperationMethod.Trace -> unsetTrace()
    }
}

fun PathItem.getOperation(operationMethod: OperationMethod): Operation? {
    return when (operationMethod) {
        OperationMethod.Get -> get
        OperationMethod.Put -> put
        OperationMethod.Post -> post
        OperationMethod.Delete -> delete
        OperationMethod.Options -> options
        OperationMethod.Head -> head
        OperationMethod.Patch -> patch
        OperationMethod.Trace -> trace
    }
}

fun PathItem.hasOperation(operationMethod: OperationMethod): Boolean {
    return getOperation(operationMethod) != null
}

fun PathItem.hasOperations(): Boolean {
    return OperationMethod.entries.any { operationMethod ->
        hasOperation(operationMethod)
    }
}
