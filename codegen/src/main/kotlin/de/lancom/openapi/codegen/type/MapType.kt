package de.lancom.openapi.codegen.type

data class MapType(
    val key: Type,
    val value: Type,
    val out: Boolean = false,
    override val required: Boolean = false,
) : Type() {
    override val type: String = "Map<${if (out) "out " else ""}${key.typeOpt}, ${value.typeOpt}>"

    override fun required(): MapType {
        return copy(required = true)
    }

    override val imports: Set<String> = key.imports + value.imports
}
