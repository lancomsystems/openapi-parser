package de.lancom.openapi.simple.entity

import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Server(
    val url: String? = null,
    val description: String? = null,
    val variables: Map<String, ServerVariable> = emptyMap(),
    override val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: Reference? = null,
) : Component<Server, ServerReference>,
    ServerOrRef, Extensions {
    fun addUrl(url: String): Server {
        return copy(url = url)
    }

    override fun addComponentName(name: String): Server {
        return addComponentReference(
            componentReference = ServerReference.fromName(
                name
            )
        )
    }

    override fun addComponentReference(componentReference: ServerReference): Server {
        return copy(componentReference = componentReference)
    }
}
