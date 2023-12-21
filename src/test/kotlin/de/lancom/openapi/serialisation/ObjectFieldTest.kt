package de.lancom.openapi.serialisation

import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field

data class ObjectFieldTest<Subject : Entity, Value : Any>(
    override val fieldName: String,
    override val setter: Subject.(Field<Value>) -> Subject,
    override val emptyYaml: String,
    override val examples: List<ObjectTestCase<Value>> = emptyList(),
    override val skipEmpty: Boolean = false,
    override val flat: Boolean = false,
) : AbstractObjectFieldTest<Subject, Value>(
    nullField = Field.unset(),
) {
    fun addExamples(vararg example: ObjectTestCase<Value>): ObjectFieldTest<Subject, Value> {
        return copy(
            examples = examples + example
        )
    }
}
