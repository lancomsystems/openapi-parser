package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.simple.types.Extension

data class Link(
    val operationId: String? = null,
    val operationRef: String? = null,
    val parameters: Map<String, String> = emptyMap(),
    val requestBody: JsonNode? = null,
    val description: String? = null,
    val server: de.lancom.openapi.simple.entity.Server? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: LinkReference? = null,
) : Component<Link, LinkReference>, LinkOrRef {
    override fun addComponentName(name: String): Link {
        return addComponentReference(componentReference = LinkReference.fromName(name))
    }

    override fun addComponentReference(componentReference: LinkReference): Link {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<LinkOrRef> {
        override val componentType: ComponentType = ComponentType.Links
    }
}
