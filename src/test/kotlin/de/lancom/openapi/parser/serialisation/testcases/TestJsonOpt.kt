package de.lancom.openapi.parser.serialisation.testcases

import de.lancom.openapi.parser.entity.*
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.serialisation.ObjectNullFieldTest
import de.lancom.openapi.parser.serialisation.ObjectTestCase

private fun <Subject : Entity, Json : Entity> testJsonOpt(
    field: String,
    setter: Subject.(Field<Json?>) -> Subject,
    factory: JsonEntityFactory<Json>,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Json> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase("int0", "0", factory(0)),
            ObjectTestCase("int1", "1", factory(1)),
            ObjectTestCase("str0", "\"0\"", factory("0")),
            ObjectTestCase("str1", "\"1\"", factory("1")),
            ObjectTestCase("true", "true", factory(true)),
            ObjectTestCase("false", "false", factory(false)),
        ),
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> exampleJsonFieldTest(
    field: String,
    setter: Subject.(Field<ExampleJson?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, ExampleJson> {
    return testJsonOpt(
        field = field,
        setter = setter,
        factory = ExampleJson,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> requestBodyJsonFieldTest(
    field: String,
    setter: Subject.(Field<RequestBodyJson?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, RequestBodyJson> {
    return testJsonOpt(
        field = field,
        setter = setter,
        factory = RequestBodyJson,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> defaultJsonFieldTest(
    field: String,
    setter: Subject.(Field<DefaultJson?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, DefaultJson> {
    return testJsonOpt(
        field = field,
        setter = setter,
        factory = DefaultJson,
        emptyYaml = emptyYaml,
    )
}
