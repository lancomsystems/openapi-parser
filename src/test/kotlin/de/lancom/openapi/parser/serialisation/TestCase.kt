package de.lancom.openapi.parser.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field
import org.junit.jupiter.api.Named
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

data class TestCase<E : Entity>(
    val description: String,
    val yamlForDeserialization: String,
    val serializedYaml: String,
    val entityField: Field<E>,
    val typeReference: TypeReference<E>,
) {
    companion object {
        operator fun <E : Entity> invoke(
            description: String,
            yaml: String,
            entity: E,
            typeReference: TypeReference<E>,
        ): TestCase<E> {
            return TestCase(description, yaml, yaml, Field(entity), typeReference)
        }

        inline operator fun <reified E : Entity> invoke(
            description: String,
            yaml: String,
            entity: E,
        ): TestCase<E> {
            return TestCase(description, yaml, yaml, Field(entity), jacksonTypeRef<E>())
        }

        inline operator fun <reified E : Entity> invoke(
            description: String,
            yamlForDeserialization: String,
            serializedYaml: String,
            entity: E,
        ): TestCase<E> {
            return TestCase(description, yamlForDeserialization, serializedYaml, Field(entity), jacksonTypeRef<E>())
        }

        operator fun <E : Entity> invoke(
            description: String,
            yamlForDeserialization: String,
            serializedYaml: String,
            entity: E,
            typeReference: TypeReference<E>,
        ): TestCase<E> {
            return TestCase(description, yamlForDeserialization, serializedYaml, Field(entity), typeReference)
        }
    }
}

fun List<TestCase<out Entity>>.toArgumentsStream(): Stream<Arguments> {
    return map { testCase ->
        Arguments.of(Named.of(testCase.description, testCase))
    }.stream()
}
