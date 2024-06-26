package de.lancom.openapi.parser.serialisation

import de.lancom.openapi.parser.entity.Entity
import de.lancom.openapi.parser.field.Field

data class ObjectNullFieldTest<Subject : Entity, Value : Any?>(
    override val fieldName: String,
    override val setter: Subject.(Field<Value?>) -> Subject,
    override val emptyYaml: String,
    override val examples: List<ObjectTestCase<Value?>> = emptyList(),
    override val skipEmpty: Boolean = false,
    override val flat: Boolean = false,
) : AbstractObjectFieldTest<Subject, Value?>(
    nullField = Field(null),
) {
    fun addExamples(vararg example: ObjectTestCase<Value?>): ObjectNullFieldTest<Subject, Value> {
        return copy(
            examples = examples + example
        )
    }
}
