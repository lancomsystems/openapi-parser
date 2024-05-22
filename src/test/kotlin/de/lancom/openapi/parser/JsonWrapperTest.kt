package de.lancom.openapi.parser

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.common.util.toYamlString
import de.lancom.openapi.common.util.yamlMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JsonWrapperTest {
    data class TestSubject(
        val map: Map<ResponseStatusCode, ResponseStatusCode> = emptyMap(),
        val responseStatusCode: ResponseStatusCode = ResponseStatusCode.DEFAULT,
    ) {
        companion object {
            val example = TestSubject(
                mapOf(ResponseStatusCode.HTTP_200_OK to ResponseStatusCode.HTTP_200_OK),
                ResponseStatusCode.HTTP_200_OK,
            )
            val yaml = """
                map:
                  "200": "200"
                responseStatusCode: "200"

            """.trimIndent()
        }
    }

    @Test
    fun testJsonWrapperToString() {
        val given = TestSubject.example
        val actual = given.toYamlString()
        val expected = TestSubject.yaml
        assertEquals(expected, actual)
    }

    @Test
    fun testJsonWrapperFromString() {
        val given = TestSubject.yaml
        val actual: TestSubject = yamlMapper.readValue(given)
        val expected = TestSubject.example
        assertEquals(expected, actual)
    }
}
