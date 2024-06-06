package de.lancom.openapi.codegen.type

data class SimpleType(
    override val type: String,
    override val imports: Set<String> = emptySet(),
    override val required: Boolean = false,
) : Type() {
    override fun required(): SimpleType {
        return copy(required = true)
    }

    companion object {
        val boolean = SimpleType("Boolean")

        val int = SimpleType("Int")

        val double = SimpleType("Double")

        val jsonNode = SimpleType(
            type = "JsonNode",
            imports = setOf(
                "com.fasterxml.jackson.databind.JsonNode",
            ),
        )

        val responseStatusCode = SimpleType(
            type = "ResponseStatusCode",
            imports = setOf(
                "de.lancom.openapi.parser.entity.ResponseStatusCode",
            ),
        )

        val securitySchemeType = SimpleType(
            type = "SecuritySchemeType",
            imports = setOf(
                "de.lancom.openapi.parser.entity.SecuritySchemeType",
            ),
        )

        val string = SimpleType("String")
    }
}
