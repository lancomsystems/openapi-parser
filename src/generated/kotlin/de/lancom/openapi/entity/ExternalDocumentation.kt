/*****************************************************************************
**   C A U T I O N                                                          **
**   This file is auto-generated!                                           **
**   If you want to make changes, please see the README.md file.            **
**   Please do not edit this file directly!                                 **
*****************************************************************************/
package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.field.Field
import de.lancom.openapi.jackson.EntityDeserializer
import de.lancom.openapi.jackson.EntitySerializer
import de.lancom.openapi.jackson.Parser
import de.lancom.openapi.jackson.Wrapper
import de.lancom.openapi.tools.toYamlString

// hint:9A1BF04C
@Suppress("PropertyName")
@JsonSerialize(using = ExternalDocumentation.Companion.Serializer::class)
@JsonDeserialize(using = ExternalDocumentation.Companion.Deserializer::class)
data class ExternalDocumentation(
    val _jsonNode: Field<JsonNode> = Field.unset(),
) : Entity {

    ///////////////////////
    //
    // jsonNode
    //
    ///////////////////////

    // hint:3A7F9B2E
    val jsonNode: JsonNode
        get() = _jsonNode.getOrError()

    // hint:F0C48D71
    fun setJsonNodeField(jsonNode: Field<JsonNode>): ExternalDocumentation {
        return copy(_jsonNode = jsonNode)
    }

    // hint:8E56A4D9
    fun updateJsonNodeField(updater: (Field<JsonNode>) -> Field<JsonNode>): ExternalDocumentation {
        return setJsonNodeField(updater(_jsonNode))
    }

    // hint:B1D730FC
    fun updateJsonNode(updater: (JsonNode) -> JsonNode): ExternalDocumentation {
        return updateJsonNodeField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeJsonNodeField(jsonNodeFieldToMerge: Field<JsonNode>): ExternalDocumentation {
        return mergeJsonNode(jsonNodeFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeJsonNode(jsonNodeToMerge: JsonNode?): ExternalDocumentation {
        return if (jsonNodeToMerge == null) {
            this
        } else {
            val oldJsonNode = _jsonNode.orNull
            if (oldJsonNode == null) {
                setJsonNodeField(Field(jsonNodeToMerge))
            } else {
                // hint:2F684DAC
                setJsonNode(jsonNodeToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setJsonNode(jsonNode: JsonNode): ExternalDocumentation {
        return setJsonNodeField(Field(jsonNode))
    }

    // hint:D465F782
    fun unsetJsonNode(): ExternalDocumentation {
        return setJsonNodeField(Field.unset())
    }

    // hint:47C9A0F6
    fun addJsonNode(jsonNode: JsonNode): ExternalDocumentation {
        return setJsonNode(jsonNode)
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = _jsonNode,
            map = mapOf(
            ),
            flatMap = listOf(
            ),
            flatten = listOf(
            ),
        )
    }

    override fun toString(): String {
        return this.toYamlString()
    }

    // hint:A0E5F382
    override fun mergeEntity(other: Entity?): ExternalDocumentation {
        return when (other) {
            null ->
                this

            is ExternalDocumentation ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: ExternalDocumentation?): ExternalDocumentation {
        if (other == null) return this
        return this
            .mergeJsonNodeField(other._jsonNode)
    }

    companion object : JsonEntityFactory<ExternalDocumentation> {

        class Serializer : EntitySerializer<ExternalDocumentation>(ExternalDocumentation::class.java, ExternalDocumentation)
        class Deserializer : EntityDeserializer<ExternalDocumentation>(ExternalDocumentation::class.java, ExternalDocumentation)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): ExternalDocumentation {
            return ExternalDocumentation(
                _jsonNode = wrapper.jsonNodeFieldOrUnsetIfEmpty,
            )
        }
    }
}
