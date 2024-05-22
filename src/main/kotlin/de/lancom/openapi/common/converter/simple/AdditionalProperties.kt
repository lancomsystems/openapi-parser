package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.simple.entity.Reference
import de.lancom.openapi.simple.entity.SchemaReference
import de.lancom.openapi.simple.types.AdditionalPropertiesSchema

fun toSimple(
    additionalProperties: de.lancom.openapi.parser.entity.AdditionalProperties?
): de.lancom.openapi.simple.types.AdditionalProperties {
    return when (additionalProperties) {
        null ->
            throw IllegalArgumentException("Unsupported additional properties null")

        is de.lancom.openapi.parser.entity.AdditionalPropertiesBoolean ->
            de.lancom.openapi.simple.types.AdditionalPropertiesBoolean

        is de.lancom.openapi.parser.entity.AdditionalPropertiesSchema ->
            additionalProperties.schema
                ?.toSimple()
                ?.let(::AdditionalPropertiesSchema)
                ?: throw IllegalArgumentException("Unsupported additional properties schema with null schema")

        is de.lancom.openapi.parser.entity.AdditionalPropertiesReference -> {
            val reference: Reference = toSimple(additionalProperties.reference)
                ?: throw IllegalArgumentException("Unsupported additional properties: schema reference is null")
            val schemaReference: SchemaReference = reference as? SchemaReference
                ?: throw IllegalArgumentException("Unsupported additional properties: reference is not a schema reference")
            AdditionalPropertiesSchema(schemaReference)
        }
    }
}
