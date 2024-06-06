package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.types.SchemaFormat
import de.lancom.openapi.common.types.SchemaType
import de.lancom.openapi.simple.types.AdditionalProperties
import de.lancom.openapi.simple.types.AdditionalPropertiesBoolean
import de.lancom.openapi.simple.types.AdditionalPropertiesSchema
import de.lancom.openapi.simple.types.Extension

data class Schema(
    val title: String? = null,
    val multipleOf: Double? = null,
    val maximum: Int? = null,
    val exclusiveMaximum: Boolean = false,
    val minimum: Int? = null,
    val exclusiveMinimum: Boolean = false,
    val maxLength: Int? = null,
    val minLength: Int = 0,
    val pattern: String? = null,
    val maxItems: Int? = null,
    val minItems: Int = 0,
    val uniqueItems: Boolean = false,
    val maxProperties: Int? = null,
    val minProperties: Int = 0,
    val required: Set<String> = emptySet(),
    val enum: Set<String> = emptySet(),
    val type: SchemaType? = null,
    val not: SchemaOrRef? = null,
    val allOf: List<SchemaOrRef> = emptyList(),
    val oneOf: List<SchemaOrRef> = emptyList(),
    val anyOf: List<SchemaOrRef> = emptyList(),
    val items: SchemaOrRef? = null,
    val properties: Map<String, SchemaOrRef> = emptyMap(),
    val additionalProperties: AdditionalProperties = AdditionalPropertiesBoolean,
    val description: String? = null,
    val format: SchemaFormat? = null,
    val default: JsonNode? = null,
    val nullable: Boolean = false,
    val discriminator: JsonNode? = null,
    val readOnly: Boolean = false,
    val writeOnly: Boolean = false,
    val example: JsonNode? = null,
    val externalDocs: JsonNode? = null,
    val deprecated: Boolean = false,
    val xml: JsonNode? = null,
    val extensions: Map<String, Extension> = emptyMap(),
    override val componentReference: SchemaReference? = null,
) : Component<Schema, SchemaReference>, SchemaOrRef {
    fun flatten(): Set<SchemaOrRef> {
        return listOf(
            listOfNotNull(
                this,
                not,
                items,
                (additionalProperties as? AdditionalPropertiesSchema)?.schemaOrRef,
            ),
            allOf,
            oneOf,
            anyOf,
            properties.values,
        ).flatten().toSet()
    }

    fun addProperty(key: String, value: SchemaOrRef): Schema {
        return copy(properties = properties + (key to value))
    }

    fun addRequired(vararg value: String): Schema {
        return copy(required = required + value)
    }

    fun addRequiredProperty(key: String, value: SchemaOrRef): Schema {
        return addProperty(key, value).addRequired(key)
    }

    fun addAllOf(vararg schema: SchemaOrRef): Schema {
        return copy(allOf = allOf + schema)
    }

    fun addItems(value: SchemaOrRef): Schema {
        return copy(items = value)
    }

    fun addDescription(value: String): Schema {
        return copy(description = value)
    }

    override fun addComponentName(name: String): Schema {
        return addComponentReference(componentReference = SchemaReference.fromName(name))
    }

    override fun addComponentReference(componentReference: SchemaReference): Schema {
        return copy(componentReference = componentReference)
    }

    companion object : ReferenceableCompanion<SchemaOrRef> {
        override val componentType: ComponentType = ComponentType.Schemas
    }
}
