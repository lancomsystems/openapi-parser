package de.lancom.openapi.codegen.type

data class ReferenceType(
    val itemType: EntityType,
    override val required: Boolean = false,
) : Type() {
    init {
        check(itemType.referenceable)
    }

    override val type: String
        get() = "Reference<${itemType.typeOpt}>"

    override val imports: Set<String>
        get() = ownImports + itemType.imports

    override fun required(): ReferenceType {
        return copy(required = true)
    }

    companion object {
        private val ownImports = setOf<String>(
            "de.lancom.openapi.parser.ref.Reference"
        )
    }
}
