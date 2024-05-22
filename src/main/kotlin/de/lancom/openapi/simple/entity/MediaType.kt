package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions

data class MediaType(
    val schema: SchemaOrRef? = null,
    val example: JsonNode? = null,
    val examples: Map<String, ExampleOrRef> = emptyMap(),
    val encoding: Map<String, Encoding> = emptyMap(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions {
    fun addSchema(schemaRef: SchemaOrRef): MediaType {
        return copy(schema = schemaRef)
    }
}
