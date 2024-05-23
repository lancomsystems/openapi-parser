package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.types.ContentType
import de.lancom.openapi.simple.types.Extension

data class RequestBody(
    val description: String? = null,
    val content: Map<ContentType, MediaType> = emptyMap(),
    val required: Boolean = false,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: RequestBodyReference? = null,
) : Component<RequestBody, RequestBodyReference>, RequestBodyOrRef {
    fun addDescription(description: String): RequestBody {
        return copy(description = description)
    }

    fun addRequired(): RequestBody {
        return copy(required = true)
    }

    fun addContent(contentType: ContentType, mediaType: MediaType): RequestBody {
        return copy(content = content + (contentType to mediaType))
    }

    fun addJsonContent(schemaRef: SchemaOrRef): RequestBody {
        return addJsonContent(MediaType().addSchema(schemaRef))
    }

    fun addJsonContent(mediaType: MediaType): RequestBody {
        return addContent(ContentType.applicationJson, mediaType)
    }

    override fun addComponentName(name: String): RequestBody {
        return addComponentReference(componentReference = RequestBodyReference.fromName(name))
    }

    override fun addComponentReference(componentReference: RequestBodyReference): RequestBody {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<RequestBodyOrRef> {
        override val componentType: ComponentType = ComponentType.RequestBodies
    }
}
