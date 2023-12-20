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
@JsonSerialize(using = Discriminator.Companion.Serializer::class)
@JsonDeserialize(using = Discriminator.Companion.Deserializer::class)
data class Discriminator(
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
    fun setJsonNodeField(jsonNode: Field<JsonNode>): Discriminator {
        return copy(_jsonNode = jsonNode)
    }

    // hint:8E56A4D9
    fun updateJsonNodeField(updater: (Field<JsonNode>) -> Field<JsonNode>): Discriminator {
        return setJsonNodeField(updater(_jsonNode))
    }

    // hint:B1D730FC
    fun updateJsonNode(updater: (JsonNode) -> JsonNode): Discriminator {
        return updateJsonNodeField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeJsonNodeField(jsonNodeFieldToMerge: Field<JsonNode>): Discriminator {
        return mergeJsonNode(jsonNodeFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeJsonNode(jsonNodeToMerge: JsonNode?): Discriminator {
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
    fun setJsonNode(jsonNode: JsonNode): Discriminator {
        return setJsonNodeField(Field(jsonNode))
    }

    // hint:D465F782
    fun unsetJsonNode(): Discriminator {
        return setJsonNodeField(Field.unset())
    }

    // hint:47C9A0F6
    fun addJsonNode(jsonNode: JsonNode): Discriminator {
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
    override fun mergeEntity(other: Entity?): Discriminator {
        return when (other) {
            null ->
                this

            is Discriminator ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: Discriminator?): Discriminator {
        if (other == null) return this
        return this
            .mergeJsonNodeField(other._jsonNode)
    }

    companion object : JsonEntityFactory<Discriminator> {

        class Serializer : EntitySerializer<Discriminator>(Discriminator::class.java, Discriminator)
        class Deserializer : EntityDeserializer<Discriminator>(Discriminator::class.java, Discriminator)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): Discriminator {
            return Discriminator(
                _jsonNode = wrapper.jsonNodeFieldOrUnsetIfEmpty,
            )
        }
    }
}
