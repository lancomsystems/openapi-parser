package de.lancom.openapi.parser

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.util.assertYamlEquals
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.Info
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.entity.Operation
import de.lancom.openapi.parser.entity.Paths
import de.lancom.openapi.parser.util.mergeOpenApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

data class MergeTestCase(
    val name: String,
    val given: Map<String, () -> OpenApi>,
    val expected: () -> OpenApi,
) {
    override fun toString(): String {
        return name
    }

    fun given(): Map<String, OpenApi> {
        return given.mapValues { (_, openApiProvider) ->
            openApiProvider()
        }
    }

    fun expected(): OpenApi {
        return expected.invoke()
    }

    companion object {
        operator fun invoke(name: String): MergeTestCase {
            return MergeTestCase(
                "$name/expected.yaml",
                "$name/given1.yaml",
                "$name/given2.yaml",
            )
        }

        operator fun invoke(expected: String, vararg given: String): MergeTestCase {
            fun parse(filename: String): () -> OpenApi {
                val file = File("src/test/resources/tests/merge", filename)
                val openApi: OpenApi by lazy {
                    yamlMapper.readValue(file)
                }
                return {
                    openApi
                }
            }
            return MergeTestCase(
                name = File(expected).parentFile.name,
                expected = parse(expected),
                given = given.associate { subject ->
                    val name = File(subject).name.replace(".yaml", "")
                    name to parse(subject)
                }
            )
        }
    }
}

class MergeTest {
    val default = OpenApi()
        .addInfo(
            Info()
                .addTitle("title")
                .addVersion("0.0.0")
        ).copy(__field_order = emptySet())

    @Test
    fun mergeEnumSchema() {
        val given1: OpenApi = yamlMapper.readValue(
            """
            components:
              schemas:
                Enum:
                  type: string
                  enum:
                    - A
                    - B
            """.trimIndent()
        )

        val given2: OpenApi = yamlMapper.readValue(
            """
            components:
              schemas:
                Enum:
                  enum:
                    - B
                    - C
            """.trimIndent()
        )

        val expected: OpenApi = yamlMapper.readValue(
            """
            components:
              schemas:
                Enum:
                  type: string
                  enum:
                    - A
                    - B
                    - C
            """.trimIndent()
        )

        val actual = given1.merge(given2)

        assertYamlEquals(
            expected = expected,
            actual = actual,
            ignoreOrder = false,
        )
    }

    @Test
    fun testFieldOrder() {
        val given = """
            /a:
            x-foo: 1
            /b:
        """.trimIndent()

        val subject: Paths = yamlMapper.readValue(given)
        val actual = subject.__field_order

        val expected = setOf(
            Paths.Companion.Fields("/a"),
            Paths.Companion.Fields("x-foo"),
            Paths.Companion.Fields("/b"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun testFieldOrderOperation() {
        val given = """
            tags: []
            summary: summary
            description: description
            parameters: []
            x-boolean: true
            responses: {}
        """.trimIndent()

        val subject: Operation = yamlMapper.readValue(given)
        val actual = subject.__field_order

        val expected = setOf(
            Operation.Companion.Fields("tags"),
            Operation.Companion.Fields("summary"),
            Operation.Companion.Fields("description"),
            Operation.Companion.Fields("parameters"),
            Operation.Companion.Fields("x-boolean"),
            Operation.Companion.Fields("responses"),
            Operation.Companion.Fields("externalDocs"),
            Operation.Companion.Fields("operationId"),
            Operation.Companion.Fields("requestBody"),
            Operation.Companion.Fields("callbacks"),
            Operation.Companion.Fields("deprecated"),
            Operation.Companion.Fields("security"),
            Operation.Companion.Fields("servers"),
        )

        assertEquals(expected, actual)
    }

    @Test
    fun testFieldOrderMerge() {
        val given1 = """
            /a: {}
            x-a: 1
            /b: {}
        """.trimIndent()

        val given2 = """
            /c: {}
            x-b: 2
            /b: {}
        """.trimIndent()

        val subject1: Paths = yamlMapper.readValue(given1)
        val subject2: Paths = yamlMapper.readValue(given2)
        val actual = subject1.merge(subject2).__field_order

        val expected = setOf(
            Paths.Companion.Fields("/a"),
            Paths.Companion.Fields("x-a"),
            Paths.Companion.Fields("/b"),
            Paths.Companion.Fields("/c"),
            Paths.Companion.Fields("x-b"),
        )

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("testcases")
    fun testParseRequiredArgs(testcase: MergeTestCase) {
        val given = testcase.given()
        val expected = testcase.expected()
        val actual = given.mergeOpenApi(
            default = default,
            patchTag = { name, tag -> "$name:$tag" },
            patchPath = { name, path -> "/cloud-service-$name$path" },
            patchOperationId = { name, operationId -> "$name${operationId.replaceFirstChar(Char::uppercaseChar)}" },
            createTagGroups = true,
        )
        assertYamlEquals(
            expected = expected,
            actual = actual,
            ignoreOrder = false,
        )
    }

    companion object {
        @JvmStatic
        fun testcases(): List<MergeTestCase> {
            return listOf(
                MergeTestCase("ext"),
                MergeTestCase("paths"),
                MergeTestCase("refs"),
                MergeTestCase("sort1"),
                MergeTestCase("sort2"),
                MergeTestCase("tags"),
            )
        }
    }
}
