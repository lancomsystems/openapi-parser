package de.lancom.openapi

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.entity.*
import de.lancom.openapi.tools.yamlMapper
import de.lancom.openapi.utils.mergeOpenApi
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
        return given.mapValues { (_, OpenApiProvider) ->
            OpenApiProvider()
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
                val OpenApi: OpenApi by lazy {
                    yamlMapper.readValue(file)
                }
                return {
                    OpenApi
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
        )

    @ParameterizedTest
    @MethodSource("testcases")
    fun testParseRequiredArgs(testcase: MergeTestCase) {
        val given = testcase.given()
        val expected = testcase.expected()
        val actual = given.mergeOpenApi(default)
        de.lancom.openapi.assertYamlEquals(
                expected = expected,
                actual = actual,
        )
    }

    companion object {
        @JvmStatic
        fun testcases(): List<MergeTestCase> {
            return listOf(
                MergeTestCase("ext"),
                MergeTestCase("paths"),
                MergeTestCase("refs"),
                MergeTestCase("tags"),
            )
        }
    }
}
