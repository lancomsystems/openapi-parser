package de.lancom.openapi.parser.serialisation

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import de.lancom.openapi.parser.entity.Entity
import kotlin.reflect.full.createInstance

inline fun <reified Subject : Entity> entityFieldTestCases(
    fieldTests: List<FieldTest<Subject, out Any?>>,
    emptyYaml: String,
    emptySubject: Subject = Subject::class.createInstance(),
): List<TestCase<Subject>> {
    val typeReference = jacksonTypeRef<Subject>()
    return listOf(
        TestCase("empty object", emptyYaml, emptySubject),
    ) + fieldTests.flatMap { fieldTest ->
        fieldTest.testCases(emptySubject, typeReference)
    }
}
