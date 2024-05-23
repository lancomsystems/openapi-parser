package de.lancom.openapi.parser.serialisation.testcases

import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.entity.Extension
import de.lancom.openapi.parser.entity.PathItem
import de.lancom.openapi.parser.entity.Response
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.serialisation.EMPTY_YAML_OBJECT
import de.lancom.openapi.parser.serialisation.ObjectFieldTest
import de.lancom.openapi.parser.serialisation.ObjectTestCase

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
