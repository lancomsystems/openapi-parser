package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.types.HeaderStyle
import de.lancom.openapi.simple.types.Extension

data class Header(
    val description: String? = null,
    val required: Boolean = false,
    val deprecated: Boolean = false,
    val allowEmptyValue: Boolean = false,
    val style: HeaderStyle = HeaderStyle.Simple,
    val explode: Boolean? = null,
    val allowReserved: Boolean = false,
    val schema: SchemaOrRef? = null,
    val content: Map<String, MediaType> = emptyMap(),
    val example: JsonNode? = null,
    val examples: Map<String, ExampleOrRef> = emptyMap(),
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: HeaderReference? = null,
) : Component<Header, HeaderReference>, HeaderOrRef {
    override fun addComponentName(name: String): Header {
        return addComponentReference(componentReference = HeaderReference.fromName(name))
    }

    override fun addComponentReference(componentReference: HeaderReference): Header {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<HeaderOrRef> {
        override val componentType: ComponentType = ComponentType.Headers
    }
}
