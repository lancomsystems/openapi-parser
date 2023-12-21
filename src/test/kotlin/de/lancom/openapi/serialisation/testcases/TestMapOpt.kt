package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.*
import de.lancom.openapi.field.Field
import de.lancom.openapi.refs.Instance
import de.lancom.openapi.refs.ReferenceOrInstance
import de.lancom.openapi.serialisation.EMPTY_YAML_OBJECT
import de.lancom.openapi.serialisation.ObjectNullFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

private fun <Subject : Entity, Key, Value> testMapOpt(
    field: String,
    setter: Subject.(Field<Map<Key, Value>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<Key, Value>?> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase("empty map", EMPTY_YAML_OBJECT, emptyMap()),
        ),
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_EncodingOpt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, Encoding?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, Encoding?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_MediaTypeOpt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, MediaType?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, MediaType?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    ).addExamples(
        ObjectTestCase(
            name = "mediaType",
            yaml = "application/json: {}",
            mapOf(ContentType.applicationJson.contentType to MediaType())
        )
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Header_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Header>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Header>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    ).addExamples(
        ObjectTestCase(
            name = "mediaType",
            yaml = "application/json: {}",
            mapOf(ContentType.applicationJson.contentType to Instance(Header()))
        )
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Link_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Link>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Link>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Schema_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Schema>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Schema>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Response_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Response>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Response>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Parameter_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Parameter>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Parameter>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_RequestBody_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<RequestBody>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<RequestBody>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_SecurityScheme_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<SecurityScheme>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<SecurityScheme>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Example_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Example>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Example>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ReferenceOr_Callback_Opt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ReferenceOrInstance<Callback>?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ReferenceOrInstance<Callback>?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ServerOpt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, Server?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, Server?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_StringOpt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, String?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, String?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> entity_field_test_Map_String_ServerVariableOpt_Opt(
    field: String,
    setter: Subject.(Field<Map<String, ServerVariable?>?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Map<String, ServerVariable?>?> {
    return testMapOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}
