package de.lancom.openapi.parser

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.util.assertYamlEquals
import de.lancom.openapi.common.util.toJsonNode
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.OpenApi
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

class ExampleTest {
    @ParameterizedTest
    @MethodSource("examples")
    fun testParseRequiredArgs(filename: String) {
        val file = File("src/test/resources", filename)
        val openApi: OpenApi = yamlMapper.readValue(file)
        assertYamlEquals(
            expectedTree = yamlMapper.readTree(file),
            actualTree = openApi.toJsonNode()
        )
    }

    companion object {
        @JvmStatic
        fun examples(): List<String> {
            return listOf(
                "tests/multiple-of.yaml",
                "tests/bug.yaml",
                "tests/empty1.yaml",
                "tests/empty2.yaml",
                "tests/info1.yaml",
                "tests/paths-empty.yaml",
                "tests/paths-get.yaml",
                "tests/schema-additional-properties.yaml",
                "examples/v30/api-with-examples.yaml",
                "examples/v30/callback-example.yaml",
                "examples/v30/link-example.yaml",
                "examples/v30/petstore-expanded.yaml",
                "examples/v30/petstore.yaml",
                "examples/v30/uspto.yaml",
            )
        }
    }
}
