package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field
import de.lancom.openapi.serialisation.EMPTY_YAML_ARRAY
import de.lancom.openapi.serialisation.ObjectNullFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

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
