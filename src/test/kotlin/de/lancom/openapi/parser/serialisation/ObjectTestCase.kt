package de.lancom.openapi.parser.serialisation

import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Reference
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

data class ObjectTestCase<T>(
    val name: String,
    val yaml: String,
    val field: Field<T>,
) {
    companion object {
        operator fun <T> invoke(
            name: String,
            yaml: String,
            value: T,
        ): ObjectTestCase<T> {
            return ObjectTestCase(name, yaml, Field(value))
        }

        operator fun <T : Referenceable> invoke(
            name: String,
            yaml: String,
            value: ReferenceOrInstance<T>,
        ): ObjectTestCase<ReferenceOrInstance<T>> {
            return ObjectTestCase(name, yaml, Field(value))
        }

        operator fun <T : Referenceable> invoke(
            name: String,
            yaml: String,
            reference: Reference<T>,
        ): ObjectTestCase<ReferenceOrInstance<T>> {
            return ObjectTestCase(name, yaml, Field(reference))
        }

        operator fun <T : Referenceable> invoke(
            name: String,
            yaml: String,
            instance: Instance<T>,
        ): ObjectTestCase<ReferenceOrInstance<T>> {
            return ObjectTestCase(name, yaml, Field(instance))
        }
    }
}
