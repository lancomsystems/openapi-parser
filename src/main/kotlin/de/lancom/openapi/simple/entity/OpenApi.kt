package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions
import de.lancom.openapi.simple.util.Resolver

data class OpenApi(
    val openapi: String? = null,
    val info: Info = Info(),
    val externalDocs: JsonNode? = null,
    val servers: List<de.lancom.openapi.simple.entity.Server> = emptyList(),
    val security: List<JsonNode> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val paths: Paths = Paths(),
    val components: Components = Components(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions, Resolver by components {
    fun addPaths(paths: Paths): OpenApi {
        return copy(paths = paths)
    }

    fun addComponents(components: Components): OpenApi {
        return copy(components = components)
    }

    fun addOpenapi30(): OpenApi {
        return addOpenapi("3.0.0")
    }

    fun addOpenapi(openapi: String): OpenApi {
        return copy(openapi = openapi)
    }

    fun addServers(servers: List<de.lancom.openapi.simple.entity.Server>): OpenApi {
        return copy(servers = servers)
    }

    fun addInfo(info: Info): OpenApi {
        return copy(info = info)
    }
}
