package de.lancom.openapi.codegen.type

data class ListType(
    override val itemType: Type,
    override val required: Boolean = false,
) : CollectionType("List") {
    override fun required(): ListType {
        return copy(required = true)
    }
}
