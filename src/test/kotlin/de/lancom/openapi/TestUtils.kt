package de.lancom.openapi

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.tools.toJsonNode
import de.lancom.openapi.tools.toYamlString
import org.junit.jupiter.api.Assertions

fun <E> assertYamlEquals(expected: E, actual: E) {
    val expectedTree: JsonNode = expected.toJsonNode()
    val actualTree: JsonNode = actual.toJsonNode()
    assertYamlEquals(expectedTree, actualTree)
    Assertions.assertEquals(expected, actual)
}

fun assertYamlEquals(expectedTree: JsonNode, actualTree: JsonNode) {
    val expected = expectedTree.toYamlString()
    val actual = actualTree.toYamlString()
    Assertions.assertEquals(expected, actual)
}
