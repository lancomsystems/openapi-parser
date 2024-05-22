package de.lancom.openapi.parser.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field

data class StringNullFieldTest<Subject : Entity>(
    val field: String,
    override val setter: Subject.(Field<String?>) -> Subject,
    val emptyYaml: String,
) : FieldTest<Subject, String?> {
    override fun testCases(empty: Subject, typeReference: TypeReference<Subject>): List<TestCase<Subject>> {
        return listOf(null, "abc", "xyz").map { value ->
            val yamlField = if (value == null) {
                "$field: null"
            } else {
                "$field: \"$value\""
            }
            val yaml = if (emptyYaml == EMPTY_YAML_OBJECT) {
                yamlField
            } else {
                listOf(
                    emptyYaml,
                    yamlField,
                ).sorted().joinToString("\n")
            }

            TestCase(
                description = "field $field with $value",
                yaml = yaml,
                entity = empty.setter(Field(value)),
                typeReference = typeReference,
            )
        } + TestCase(
            description = "field $field with unset",
            yaml = emptyYaml,
            entity = empty.setter(Field.unset()),
            typeReference = typeReference,
        )
    }
}
