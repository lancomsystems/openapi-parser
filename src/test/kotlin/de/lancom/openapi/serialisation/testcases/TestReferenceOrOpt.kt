package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.Entity
import de.lancom.openapi.entity.RequestBody
import de.lancom.openapi.entity.Schema
import de.lancom.openapi.field.Field
import de.lancom.openapi.refs.Instance
import de.lancom.openapi.refs.Reference
import de.lancom.openapi.refs.ReferenceOrInstance
import de.lancom.openapi.refs.Referenceable
import de.lancom.openapi.serialisation.EMPTY_YAML_OBJECT
import de.lancom.openapi.serialisation.ObjectNullFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

private fun <Subject : Entity, Value : Referenceable> testReferenceOrInstanceOpt(
    field: String,
    setter: Subject.(Field<ReferenceOrInstance<Value>?>) -> Subject,
    emptyField: Value,
    emptyYaml: String,
    reference: Reference<Value>,
    flat: Boolean,
): ObjectNullFieldTest<Subject, ReferenceOrInstance<Value>?> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        examples = listOf(
            ObjectTestCase<ReferenceOrInstance<Value>?>("empty object", EMPTY_YAML_OBJECT, Instance(emptyField)),
            ObjectTestCase<ReferenceOrInstance<Value>?>("reference", "\$ref: \"${reference.ref}\"", reference),
        ),
        flat = flat,
        emptyYaml = emptyYaml,
    )
}

fun <Subject : Entity> schemaRefFieldTest(
    field: String,
    setter: Subject.(Field<ReferenceOrInstance<Schema>?>) -> Subject,
    emptyYaml: String,
    flat: Boolean = false,
): ObjectNullFieldTest<Subject, ReferenceOrInstance<Schema>?> {
    return testReferenceOrInstanceOpt(
        field = field,
        setter = setter,
        emptyField = Schema(),
        emptyYaml = emptyYaml,
        flat = flat,
        reference = Reference("some/ref"),
    )
}

fun <Subject : Entity> requestBodyRefFieldTest(
    field: String,
    setter: Subject.(Field<ReferenceOrInstance<RequestBody>?>) -> Subject,
    emptyYaml: String,
    flat: Boolean = false,
): ObjectNullFieldTest<Subject, ReferenceOrInstance<RequestBody>?> {
    return testReferenceOrInstanceOpt(
        field = field,
        setter = setter,
        emptyField = RequestBody(),
        emptyYaml = emptyYaml,
        flat = flat,
        reference = Reference("some/ref"),
    )
}
