package de.lancom.openapi.codegen.type

data class EnumType(
    val enum: String,
    val values: List<String>,
    override val required: Boolean = false,
) : Type() {
    override val type: String = enum

    override fun required(): EnumType {
        return copy(required = true)
    }

    companion object {
        val HeaderStyle = EnumType(
            enum = "HeaderStyle",
            values = listOf(
                "simple",
            ),
        )

        val In = EnumType(
            enum = "In",
            values = listOf(
                "path",
                "query",
                "header",
                "cookie",
            ),
        )

        val InApiKey = EnumType(
            enum = "InApiKey",
            values = listOf(
                "header",
                "query",
                "cookie",
            ),
        )

        val SchemaType = EnumType(
            enum = "SchemaType",
            values = listOf(
                "array",
                "boolean",
                "integer",
                "number",
                "object",
                "string",
            ),
        )

        val Style = EnumType(
            enum = "Style",
            values = listOf(
                "form",
                "spaceDelimited",
                "pipeDelimited",
                "deepObject",
            ),
        )

        private val ParameterStyle = EnumType(
            enum = "ParameterStyle",
            values = listOf(
                "",
                "matrix",
                "label",
                "form",
                "spaceDelimited",
                "pipeDelimited",
                "deepObject",
                "simple",
            ),
        )

        private val ComponentType = EnumType(
            enum = "ComponentType",
            values = listOf(
                "callbacks",
                "examples",
                "headers",
                "links",
                "operations",
                "parameters",
                "pathItems",
                "requestBodies",
                "responses",
                "schemas",
                "securitySchemes",
                "servers",
            ),
        )

        val all: Set<EnumType> = setOf(
            HeaderStyle,
            In,
            InApiKey,
            SchemaType,
            Style,
            ParameterStyle,
            ComponentType,
        )
    }
}
