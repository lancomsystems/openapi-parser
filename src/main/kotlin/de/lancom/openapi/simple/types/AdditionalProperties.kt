package de.lancom.openapi.simple.types

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
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

class AdditionalPropertiesSerializer : JsonSerializer<AdditionalProperties>() {
    override fun serialize(
        value: AdditionalProperties,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        when (value) {
            AdditionalPropertiesBoolean ->
                gen.writeBoolean(true)

            is AdditionalPropertiesSchema ->
                gen.writeObject(value.schemaOrRef)
        }
    }
}

class AdditionalPropertiesDeserializer : JsonDeserializer<AdditionalProperties>() {
    override fun deserialize(
        p: JsonParser,
        ctxt: DeserializationContext
    ): AdditionalProperties {
        val value = p.valueAsString
        return if (value == "true") {
            AdditionalPropertiesBoolean
        } else {
            AdditionalPropertiesSchema(p.readValueAs(SchemaOrRef::class.java))
        }
    }
}
