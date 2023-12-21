package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.*
import de.lancom.openapi.field.Field
import de.lancom.openapi.refs.ReferenceOrInstance
import de.lancom.openapi.serialisation.EMPTY_YAML_OBJECT
import de.lancom.openapi.serialisation.ObjectFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

private fun <Subject : Entity, Key, Value> testMapUnsetIfEmpty(
    field: String,
    setter: Subject.(Field<Map<Key, Value>>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, Map<Key, Value>> {
    return ObjectFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase("empty map", EMPTY_YAML_OBJECT, Field.unset()),
        ),
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_PathItemOpt(
    field: String,
    setter: Subject.(Field<Map<String, PathItem?>>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, Map<String, PathItem?>> {
    return testMapUnsetIfEmpty(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ExtensionOpt(
    field: String,
    setter: Subject.(Field<Map<String, Extension?>>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, Map<String, Extension?>> {
    return testMapUnsetIfEmpty(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_ResponseStatusCode_ReferenceOr_Response_Opt(
    field: String,
    setter: Subject.(Field<Map<ResponseStatusCode, ReferenceOrInstance<Response>?>>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, Map<ResponseStatusCode, ReferenceOrInstance<Response>?>> {
    return testMapUnsetIfEmpty(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}
