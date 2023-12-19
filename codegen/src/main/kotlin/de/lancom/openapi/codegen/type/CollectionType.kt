package de.lancom.openapi.codegen.type

sealed class CollectionType(
    val typeName: String
) : Type() {
    abstract val itemType: Type

    override val type: String
        get() = "$typeName<${itemType.typeOpt}>"

    override val imports: Set<String>
        get() = itemType.imports
}
