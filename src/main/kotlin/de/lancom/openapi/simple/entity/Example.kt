package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.simple.types.Extension

data class Example(
    val summary: String? = null,
    val description: String? = null,
    val value: JsonNode? = null,
    val externalValue: String? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: ExampleReference? = null,
) : Component<Example, ExampleReference>, ExampleOrRef {
    override fun addComponentName(name: String): Example {
        return addComponentReference(componentReference = ExampleReference.fromName(name))
    }

    override fun addComponentReference(componentReference: ExampleReference): Example {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<ExampleOrRef> {
        override val componentType: ComponentType = ComponentType.Examples
    }
}
