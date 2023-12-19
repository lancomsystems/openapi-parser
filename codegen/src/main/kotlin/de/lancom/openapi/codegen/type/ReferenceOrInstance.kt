package de.lancom.openapi.codegen.type

data class ReferenceOrInstance(
    val itemType: EntityType,
    override val required: Boolean = false,
) : Type() {
    init {
        check(itemType.referenceable)
    }

    override val type: String
        get() = "ReferenceOrInstance<${itemType.typeOpt}>"

    override val imports: Set<String>
        get() = ownImports + itemType.imports

    override fun required(): ReferenceOrInstance {
        return copy(required = true)
    }

    companion object {
        private val ownImports = setOf(
            "de.lancom.openapi.refs.ReferenceOrInstance"
        )
    }
}
