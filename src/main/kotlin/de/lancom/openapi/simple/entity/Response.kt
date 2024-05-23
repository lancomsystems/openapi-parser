package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.types.ContentType
import de.lancom.openapi.simple.types.Extension

data class Response(
    val description: String? = null,
    val headers: Map<String, HeaderOrRef> = emptyMap(),
    val content: Map<ContentType, MediaType> = emptyMap(),
    val links: Map<String, LinkOrRef> = emptyMap(),
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: ResponseReference? = null,
) : Component<Response, ResponseReference>, ResponseOrRef {
    fun addContent(contentType: ContentType, mediaType: MediaType): Response {
        return copy(content = content + (contentType to mediaType))
    }

    fun addJsonContent(schemaRef: SchemaOrRef): Response {
        return addJsonContent(MediaType().addSchema(schemaRef))
    }

    fun addJsonContent(mediaType: MediaType): Response {
        return addContent(ContentType.applicationJson, mediaType)
    }

    fun addDescription(description: String): Response {
        return copy(description = description)
    }

    override fun addComponentName(name: String): Response {
        return addComponentReference(componentReference = ResponseReference.fromName(name))
    }

    override fun addComponentReference(componentReference: ResponseReference): Response {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<ResponseOrRef> {
        override val componentType: ComponentType = ComponentType.Responses
    }
}
