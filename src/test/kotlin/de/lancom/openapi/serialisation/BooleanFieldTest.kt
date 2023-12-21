package de.lancom.openapi.serialisation

import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field

data class BooleanFieldTest<Subject : Entity>(
    override val field: String,
    override val setter: Subject.(Field<Boolean>) -> Subject,
    override val emptyYaml: String,
    override val flat: Boolean,
) : AbstractBooleanFieldTest<Subject, Boolean>(listOf(true, false))
