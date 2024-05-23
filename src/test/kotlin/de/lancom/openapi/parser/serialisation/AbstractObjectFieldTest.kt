package de.lancom.openapi.parser.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field

abstract class AbstractObjectFieldTest<Subject : Entity, Value : Any?>(
    private val nullField: Field<Value>,
) : FieldTest<Subject, Value> {
    abstract val fieldName: String
    abstract val emptyYaml: String
    abstract val skipEmpty: Boolean
    abstract val flat: Boolean
    abstract val examples: List<ObjectTestCase<Value>>

    override fun testCases(empty: Subject, typeReference: TypeReference<Subject>): List<TestCase<Subject>> {
        return examples.mapNotNull { (name, yaml, field) ->
            if (field.isDefined) {
                val value = field.getOrError()

                val indented by lazy {
                    yaml.split("\n").map { line ->
                        "  $line"
                    }.joinToString("\n")
                }

                val y = when {
                    flat ->
                        yaml

                    value == null ->
                        "$fieldName: null"

                    value is Map<*, *> && yaml != emptyYaml ->
                        "$fieldName:\n$indented"

                    value is Entity && value.entityDescriptor.jsonNode?.isDefined == true ->
                        "$fieldName: $yaml"

                    value is Entity && yaml == emptyYaml ->
                        "$fieldName: $yaml"

                    value is Entity && value.entityDescriptor.isFlat ->
                        "$fieldName: $yaml"

                    value is Entity ->
                        "$fieldName:\n$indented"

                    yaml.startsWith("-") ->
                        "$fieldName:\n$yaml"

                    yaml == EMPTY_YAML_ARRAY ->
                        "$fieldName: []"

                    value is Collection<*> ->
                        "$fieldName:\n- $yaml"

                    else ->
                        "$fieldName: $yaml"
                }

                val valueIsEmpty = skipEmpty && (value == null || (value is Collection<*> && value.isEmpty()))

                TestCase(
                    description = "field $fieldName with $name",
                    yamlForDeserialization = y,
                    serializedYaml = if (valueIsEmpty) emptyYaml else y,
                    entity = empty.setter(field),
                    typeReference = typeReference,
                )
            } else {
                null
            }
        } + listOfNotNull(
            TestCase(
                description = "field $fieldName with unset",
                yaml = emptyYaml,
                entity = empty.setter(Field.unset()),
                typeReference = typeReference,
            ),
            if (nullField.isDefined) {
                TestCase(
                    description = "field $fieldName with null",
                    yaml = if (flat) {
                        "null"
                    } else {
                        "$fieldName: null"
                    },
                    entity = empty.setter(nullField),
                    typeReference = typeReference,
                )
            } else {
                null
            },
        )
    }
}
