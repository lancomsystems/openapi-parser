package de.lancom.openapi.parser.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field

interface FieldTest<Subject : Entity, Value : Any?> {
    val setter: Subject.(Field<Value>) -> Subject

    fun testCases(empty: Subject, typeReference: TypeReference<Subject>): List<TestCase<Subject>>
}
