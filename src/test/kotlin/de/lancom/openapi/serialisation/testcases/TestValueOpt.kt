package de.lancom.openapi.serialisation.testcases

import de.lancom.openapi.entity.*
import de.lancom.openapi.field.Field
import de.lancom.openapi.serialisation.EMPTY_YAML_OBJECT
import de.lancom.openapi.serialisation.ObjectNullFieldTest
import de.lancom.openapi.serialisation.ObjectTestCase

private fun <Subject : Entity, Value : Any?> testValueOpt(
    field: String,
    setter: Subject.(Field<Value?>) -> Subject,
    emptyField: Value,
    emptyYaml: String,
    skipEmpty: Boolean = false,
    flat: Boolean = false,
): ObjectNullFieldTest<Subject, Value> {
    return ObjectNullFieldTest(
        fieldName = field,
        setter = setter,
        emptyYaml = emptyYaml,
        examples = listOf(
            ObjectTestCase("empty object", EMPTY_YAML_OBJECT, emptyField),
        ),
        skipEmpty = skipEmpty,
        flat = flat,
    )
}

fun <Subject : Entity> schemaFieldTest(
    field: String,
    setter: Subject.(Field<Schema?>) -> Subject,
    emptyYaml: String,
    flat: Boolean = false,
): ObjectNullFieldTest<Subject, Schema?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyField = Schema(),
        emptyYaml = emptyYaml,
        flat = flat,
    )
}

fun <Subject : Entity> operationFieldTest(
    field: String,
    setter: Subject.(Field<Operation?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Operation?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Operation(),
    )
}

fun <Subject : Entity> contactFieldTest(
    field: String,
    setter: Subject.(Field<Contact?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Contact?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Contact(),
    )
}

fun <Subject : Entity> responsesFieldTest(
    field: String,
    setter: Subject.(Field<Responses?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Responses?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Responses(),
        skipEmpty = true,
    )
}

fun <Subject : Entity> licenseFieldTest(
    field: String,
    setter: Subject.(Field<License?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, License?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = License(),
    )
}


fun <Subject : Entity> discriminatorFieldTest(
    field: String,
    setter: Subject.(Field<Discriminator?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Discriminator?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Discriminator(),
    )
}

fun <Subject : Entity> serverFieldTest(
    field: String,
    setter: Subject.(Field<Server?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Server?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Server(),
    )
}

fun <Subject : Entity> externalDocumentationFieldTest(
    field: String,
    setter: Subject.(Field<ExternalDocumentation?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, ExternalDocumentation?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = ExternalDocumentation(),
    )
}

fun <Subject : Entity> infoFieldTest(
    field: String,
    setter: Subject.(Field<Info?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Info?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Info(),
    )
}

fun <Subject : Entity> pathsFieldTest(
    field: String,
    setter: Subject.(Field<Paths?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Paths?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Paths(),
    )
}

fun <Subject : Entity> componentsFieldTest(
    field: String,
    setter: Subject.(Field<Components?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, Components?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = Components(),
    )
}

fun <Subject : Entity> xMLFieldTest(
    field: String,
    setter: Subject.(Field<XML?>) -> Subject,
    emptyYaml: String,
): ObjectNullFieldTest<Subject, XML?> {
    return testValueOpt(
        field = field,
        setter = setter,
        emptyYaml = emptyYaml,
        emptyField = XML(),
    )
}
