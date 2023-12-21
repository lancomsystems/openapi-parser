package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.AdditionalProperties
import de.lancom.openapi.entity.AdditionalPropertiesBoolean
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.entity.Schema
import de.lancom.openapi.field.Field
import de.lancom.openapi.refs.Reference
import de.lancom.openapi.serialisation.ObjectFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

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
