package de.lancom.openapi.entity

import de.lancom.openapi.field.Field

enum class OperationType {
    get,
    put,
    post,
    delete,
    options,
    head,
    patch,
    trace,
    ;

    operator fun get(pathItem: PathItem): Operation? {
        return when (this) {
            get -> pathItem.get
            put -> pathItem.put
            post -> pathItem.post
            delete -> pathItem.delete
            options -> pathItem.options
            head -> pathItem.head
            patch -> pathItem.patch
            trace -> pathItem.trace
        }
    }

    fun clear(pathItem: PathItem): PathItem {
        return set(pathItem, Field.unset())
    }

    fun set(pathItem: PathItem, operation: Field<Operation?>): PathItem {
        return when (this) {
            get -> pathItem.setGetField(operation)
            put -> pathItem.setPutField(operation)
            post -> pathItem.setPostField(operation)
            delete -> pathItem.setDeleteField(operation)
            options -> pathItem.setOptionsField(operation)
            head -> pathItem.setHeadField(operation)
            patch -> pathItem.setPatchField(operation)
            trace -> pathItem.setTraceField(operation)
        }
    }

    companion object {
        operator fun invoke(pathItem: PathItem): Map<OperationType, Operation> {
            return OperationType.values().mapNotNull { operationType ->
                operationType[pathItem]?.let { operation ->
                    operationType to operation
                }
            }.toMap()
        }

        fun clear(pathItem: PathItem): PathItem {
            return OperationType.values().fold(pathItem) { item, operationType ->
                operationType.clear(item)
            }
        }

        fun hasOperations(pathItem: PathItem): Boolean {
            return pathItem != clear(pathItem)
        }

        fun set(pathItem: PathItem, items: Map<OperationType, Operation>): PathItem {
            return items.toList().fold(clear(pathItem)) { item, pair ->
                val (operationType, operation) = pair
                operationType.set(item, Field(operation))
            }
        }
    }
}
