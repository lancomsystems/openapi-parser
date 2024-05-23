package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class Responses(
    val responses: Map<ResponseStatusCode, ResponseOrRef> = emptyMap(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addResponses(responses: Map<ResponseStatusCode, ResponseOrRef>): Responses {
        return copy(responses = this.responses + responses)
    }
}
