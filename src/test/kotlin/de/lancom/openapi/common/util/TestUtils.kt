package de.lancom.openapi.common.util

import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions

fun <E : Any> assertYamlEquals(expected: E, actual: E, ignoreOrder: Boolean = true) {
    val expectedTree: JsonNode = expected.toJsonNode()
    val actualTree: JsonNode = actual.toJsonNode()
    assertYamlEquals(expectedTree, actualTree, ignoreOrder = ignoreOrder)
    Assertions.assertEquals(expected, actual)
}

fun assertYamlEquals(expectedTree: JsonNode, actualTree: JsonNode, ignoreOrder: Boolean = true) {
    val expected = expectedTree.toYamlString(ignoreOrder = ignoreOrder)
    val actual = actualTree.toYamlString(ignoreOrder = ignoreOrder)
    Assertions.assertEquals(expected, actual)
}
