package de.lancom.openapi.serialisation

import de.lancom.openapi.assertYamlEquals
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.tools.yamlMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

abstract class AbstractSerialisationTest {
    @ParameterizedTest
    @MethodSource("testCases")
    fun testDeserialisation(testCase: TestCase<Entity>) {
        val actual = try {
            yamlMapper.readValue(testCase.yamlForDeserialization, testCase.typeReference)
        } catch (exception: Exception) {
            println("failed to deserialize yaml:")
            println(testCase.yamlForDeserialization)
            throw RuntimeException(exception)
        }
        val expected = testCase.entityField.getOrError()
        assertYamlEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun testSerialisation(testCase: TestCase<Entity>) {
        val given = testCase.entityField.getOrError()
        val actual = try {
            yamlMapper.writeValueAsString(given)
        } catch (exception: Exception) {
            println("failed to serialize to yaml:")
            println(testCase.entityField.getOrError())
            throw RuntimeException(exception)
        }
        val expected = testCase.serializedYaml + "\n"
        assertEquals(expected, actual)
    }
}
