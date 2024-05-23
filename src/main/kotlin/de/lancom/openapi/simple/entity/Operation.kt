package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.common.types.TagRef
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Operation(
    val tags: List<TagRef> = emptyList(),
    val summary: String? = null,
    val description: String? = null,
    val externalDocs: JsonNode? = null,
    val operationId: String? = null,
    val parameters: List<ParameterOrRef> = emptyList(),
    val requestBody: RequestBodyOrRef? = null,
    val responses: Responses = Responses(),
    val callbacks: Map<String, CallbackOrRef> = emptyMap(),
    val deprecated: Boolean = false,
    val security: List<JsonNode> = emptyList(),
    val servers: List<de.lancom.openapi.simple.entity.Server> = emptyList(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addOperationId(operationId: String): Operation {
        return copy(operationId = operationId)
    }

    fun addDescription(description: String): Operation {
        return copy(description = description)
    }

    fun addParameters(vararg parameters: ParameterOrRef): Operation {
        return copy(parameters = this.parameters + parameters)
    }

    fun addResponses(vararg responses: Pair<ResponseStatusCode, ResponseOrRef>): Operation {
        return addResponses(responses.toMap())
    }

    fun addResponses(responses: Map<ResponseStatusCode, ResponseOrRef>): Operation {
        return copy(responses = this.responses.addResponses(responses))
    }

    fun addResponse(pair: Pair<ResponseStatusCode, ResponseOrRef>): Operation {
        return addResponses(mapOf(pair))
    }

    fun addRequestBody(requestBody: RequestBodyOrRef): Operation {
        return copy(requestBody = requestBody)
    }
}
