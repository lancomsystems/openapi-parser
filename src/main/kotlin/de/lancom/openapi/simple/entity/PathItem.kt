package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class PathItem(
    val summary: String? = null,
    val description: String? = null,
    val servers: List<de.lancom.openapi.simple.entity.Server> = emptyList(),
    val parameters: List<ParameterOrRef> = emptyList(),
    val get: Operation? = null,
    val put: Operation? = null,
    val post: Operation? = null,
    val delete: Operation? = null,
    val options: Operation? = null,
    val head: Operation? = null,
    val patch: Operation? = null,
    val trace: Operation? = null,
    override val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: PathItemReference? = null,
) : Component<PathItem, PathItemReference>, PathItemOrRef, Extensions {
    fun addGet(operation: Operation): PathItem {
        return copy(get = operation)
    }

    fun addPost(operation: Operation): PathItem {
        return copy(post = operation)
    }

    fun addDelete(operation: Operation): PathItem {
        return copy(delete = operation)
    }

    fun setOperation(
        operationMethod: OperationMethod,
        operation: Operation?,
    ): PathItem {
        return when (operationMethod) {
            OperationMethod.Get -> copy(get = operation)
            OperationMethod.Put -> copy(put = operation)
            OperationMethod.Post -> copy(post = operation)
            OperationMethod.Delete -> copy(delete = operation)
            OperationMethod.Options -> copy(options = operation)
            OperationMethod.Head -> copy(head = operation)
            OperationMethod.Patch -> copy(patch = operation)
            OperationMethod.Trace -> copy(trace = operation)
        }
    }

    fun getOperation(
        operationMethod: OperationMethod,
    ): Operation? {
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

    fun hasOperation(
        operationMethod: OperationMethod,
    ): Boolean {
        return getOperation(operationMethod) != null
    }

    fun hasOperations(): Boolean {
        return OperationMethod.entries.any { operationMethod ->
            hasOperation(operationMethod)
        }
    }

    override fun addComponentName(name: String): PathItem {
        return addComponentReference(componentReference = PathItemReference.fromName(name))
    }

    override fun addComponentReference(componentReference: PathItemReference): PathItem {
        return copy(componentReference = componentReference)
    }
}
