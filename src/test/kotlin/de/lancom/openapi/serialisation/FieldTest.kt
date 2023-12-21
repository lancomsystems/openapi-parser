package de.lancom.openapi.serialisation

import com.fasterxml.jackson.core.type.TypeReference
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field

interface FieldTest<Subject : Entity, Value : Any?> {
    val setter: Subject.(Field<Value>) -> Subject

    fun testCases(empty: Subject, typeReference: TypeReference<Subject>): List<TestCase<Subject>>
}
