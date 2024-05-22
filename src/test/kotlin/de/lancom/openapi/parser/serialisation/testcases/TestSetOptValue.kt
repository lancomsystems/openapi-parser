package de.lancom.openapi.parser.serialisation.testcases

import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.serialisation.EMPTY_YAML_ARRAY
import de.lancom.openapi.parser.serialisation.ObjectNullFieldTest
import de.lancom.openapi.parser.serialisation.ObjectTestCase

private fun <Subject : Entity, Value> testSetOptValue(
    field: String,
    setter: Subject.(Field<Set<Value>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Set<Value>?> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase("empty set", EMPTY_YAML_ARRAY, emptySet()),
        ),
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entityFieldStringOptSetTest(
    field: String,
    setter: Subject.(Field<Set<String?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Set<String?>?> {
    return testSetOptValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}
