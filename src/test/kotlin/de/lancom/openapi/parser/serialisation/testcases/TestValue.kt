package de.lancom.openapi.parser.serialisation.testcases

import de.lancom.openapi.parser.entity.AdditionalProperties
import de.lancom.openapi.parser.entity.AdditionalPropertiesBoolean
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.entity.Schema
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.Reference
import de.lancom.openapi.parser.serialisation.ObjectFieldTest
import de.lancom.openapi.parser.serialisation.ObjectTestCase

private fun <Subject : Entity, Value : Any> testValue(
    field: String,
    setter: Subject.(Field<Value>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, Value> {
    return ObjectFieldTest(
        fieldName = field,
        setter = setter,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> additionalPropertiesFieldTest(
    field: String,
    setter: Subject.(Field<AdditionalProperties>) -> Subject,
    emptyYaml: String,
): ObjectFieldTest<Subject, AdditionalProperties> {
    return testValue(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
    ).addExamples(
        ObjectTestCase("true", "true", AdditionalPropertiesBoolean),
        ObjectTestCase("Schema", "type: \"object\"", AdditionalProperties(Schema().addTypeObject())),
        ObjectTestCase("Ref", "\$ref: \"ref\"", AdditionalProperties(Reference("ref"))),
    )
}
