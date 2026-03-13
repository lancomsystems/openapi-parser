package de.lancom.openapi.simple.types

import tools.jackson.core.JsonGenerator
import tools.jackson.core.JsonParser
import tools.jackson.databind.DeserializationContext
import tools.jackson.databind.SerializationContext
import tools.jackson.databind.ValueDeserializer
import tools.jackson.databind.ValueSerializer
import tools.jackson.databind.annotation.JsonDeserialize
import tools.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.simple.entity.SchemaOrRef

@JsonSerialize(using = AdditionalPropertiesSerializer::class)
@JsonDeserialize(using = AdditionalPropertiesDeserializer::class)
sealed interface AdditionalProperties

data object AdditionalPropertiesBoolean : AdditionalProperties {
    const val value: Boolean = true
}

data class AdditionalPropertiesSchema(
    val schemaOrRef: SchemaOrRef,
) : AdditionalProperties

class AdditionalPropertiesSerializer : ValueSerializer<AdditionalProperties>() {
    override fun serialize(
        value: AdditionalProperties,
        gen: JsonGenerator,
        serializers: SerializationContext,
    ) {
        when (value) {
            AdditionalPropertiesBoolean ->
                gen.writeBoolean(true)

            is AdditionalPropertiesSchema ->
                gen.writePOJO(value.schemaOrRef)
        }
    }
}

class AdditionalPropertiesDeserializer : ValueDeserializer<AdditionalProperties>() {
    override fun deserialize(
        p: JsonParser,
        ctxt: DeserializationContext,
    ): AdditionalProperties {
        val value = p.valueAsString
        return if (value == "true") {
            AdditionalPropertiesBoolean
        } else {
            AdditionalPropertiesSchema(p.readValueAs(SchemaOrRef::class.java))
        }
    }
}
