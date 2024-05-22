package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.types.In
import de.lancom.openapi.common.types.ParameterStyle
import de.lancom.openapi.simple.types.Extension

data class Parameter(
    val name: String? = null,
    val `in`: In? = null,
    val description: String? = null,
    val required: Boolean = false,
    val deprecated: Boolean = false,
    val allowEmptyValue: Boolean = false,
    val style: ParameterStyle = ParameterStyle.Default,
    val explode: Boolean? = null,
    val allowReserved: Boolean = false,
    val schema: SchemaOrRef? = null,
    val content: Map<String, MediaType> = emptyMap(),
    val example: JsonNode? = null,
    val examples: Map<String, ExampleOrRef> = emptyMap(),
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: ParameterReference? = null,
) : Component<Parameter, ParameterReference>, ParameterOrRef {
    fun addDescription(description: String): Parameter {
        return copy(description = description)
    }

    override fun addComponentName(name: String): Parameter {
        return addComponentReference(componentReference = ParameterReference.fromName(name))
    }

    override fun addComponentReference(componentReference: ParameterReference): Parameter {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<ParameterOrRef> {
        override val componentType: ComponentType = ComponentType.Parameters
    }
}
