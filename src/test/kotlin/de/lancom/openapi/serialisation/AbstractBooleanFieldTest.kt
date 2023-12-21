package de.lancom.openapi.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field

abstract class AbstractBooleanFieldTest<Subject : Entity, B : Boolean?>(
    val cases: List<B>,
) : FieldTest<Subject, B> {
    abstract val field: String
    abstract val emptyYaml: String
    abstract val flat: Boolean

    override fun testCases(empty: Subject, typeReference: TypeReference<Subject>): List<TestCase<Subject>> {
        return cases.map { value ->
            TestCase(
                description = "field $field with $value",
                yaml = if (flat) {
                    "$value"
                } else {
                    "$field: $value"
                },
                entity = empty.setter(Field(value)),
                typeReference = typeReference,
            )
        } + listOf(
            TestCase(
                description = "field $field empty",
                yaml = emptyYaml,
                entity = empty,
                typeReference = typeReference,
            ),
            TestCase(
                description = "field $field unset",
                yaml = emptyYaml,
                entity = empty.setter(Field.unset()),
                typeReference = typeReference,
            ),
        )
    }
}
