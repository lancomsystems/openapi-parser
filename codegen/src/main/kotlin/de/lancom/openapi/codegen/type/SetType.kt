package de.lancom.openapi.codegen.type

data class SetType(
    override val itemType: Type,
    override val required: Boolean = false,
) : CollectionType("Set") {
    override fun required(): SetType {
        return copy(required = true)
    }
}

