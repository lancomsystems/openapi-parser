package de.lancom.openapi.parser.serialisation.testcases

import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.entity.SecurityRequirement
import de.lancom.openapi.parser.entity.Server
import de.lancom.openapi.parser.entity.Tag
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.serialisation.EMPTY_YAML_ARRAY
import de.lancom.openapi.parser.serialisation.ObjectNullFieldTest
import de.lancom.openapi.parser.serialisation.ObjectTestCase

private fun <Subject : Entity, Value> testListOptValue(
    field: String,
    setter: Subject.(Field<List<Value>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, List<Value>?> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase("empty list", EMPTY_YAML_ARRAY, emptyList()),
        ),
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entityFieldStringOptListTest(
    field: String,
    setter: Subject.(Field<List<String?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, List<String?>?> {
    return testListOptValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> serverListFieldTest(
    field: String,
    setter: Subject.(Field<List<Server?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, List<Server?>?> {
    return testListOptValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> securityRequirementListFieldTest(
    field: String,
    setter: Subject.(Field<List<SecurityRequirement?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, List<SecurityRequirement?>?> {
    return testListOptValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> tagListFieldTest(
    field: String,
    setter: Subject.(Field<List<Tag?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, List<Tag?>?> {
    return testListOptValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}
