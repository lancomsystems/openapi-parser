package de.lancom.openapi.entity

import de.lancom.openapi.jackson.EntityDeserializer
import de.lancom.openapi.jackson.EntitySerializer
import de.lancom.openapi.jackson.Parser
import de.lancom.openapi.jackson.Wrapper
import de.lancom.openapi.refs.Instance
import de.lancom.openapi.refs.Reference
import de.lancom.openapi.refs.ReferenceOrInstance
import de.lancom.openapi.refs.Referenceable

sealed interface AdditionalProperties : Referenceable {
    fun mergeEntity(other: AdditionalProperties?): AdditionalProperties {
        if (other == null) {
            return this
        }
        return when (this) {

            is AdditionalPropertiesBoolean ->
                other

            is AdditionalPropertiesReference ->
                other

            is AdditionalPropertiesSchema ->
                merge(other)
        }
    }

    companion object : Parser<AdditionalProperties> {
        class Serializer : EntitySerializer<AdditionalProperties>(
            AdditionalProperties::class.java,
            AdditionalProperties
        )

        class Deserializer : EntityDeserializer<AdditionalProperties>(
            AdditionalProperties::class.java,
            AdditionalProperties
        )

        operator fun invoke(boolean: Boolean): AdditionalProperties {
            return if (boolean) {
                AdditionalPropertiesBoolean
            } else {
                TODO("AdditionalProperties false is not supported")
            }
        }

        operator fun invoke(schema: Schema): AdditionalPropertiesSchema {
            return AdditionalPropertiesSchema().addSchema(schema)
        }

        operator fun invoke(reference: Reference<Schema>): AdditionalPropertiesReference {
            return AdditionalPropertiesReference().addReference(reference)
        }

        operator fun invoke(reference: String): AdditionalPropertiesReference {
            return AdditionalProperties(Reference(reference))
        }

        operator fun invoke(instance: Instance<Schema>): AdditionalPropertiesSchema {
            return AdditionalPropertiesSchema().addSchema(instance.referenced)
        }

        operator fun invoke(referenceOrInstance: ReferenceOrInstance<Schema>): AdditionalProperties {
            return when (referenceOrInstance) {
                is Reference<Schema> ->
                    AdditionalProperties(referenceOrInstance)

                is Instance<Schema> ->
                    AdditionalProperties(referenceOrInstance)
            }
        }

        operator fun invoke(wrapper: Wrapper): AdditionalProperties {
            val jsonNode = wrapper.jsonNodeField.orNull
            return when {
                jsonNode == null ->
                    TODO()

                jsonNode.isNull ->
                    TODO("null is not allowed for AdditionalProperties")

                jsonNode.isBoolean ->
                    AdditionalProperties(wrapper.getBoolean().getOrError())

                jsonNode.isObject -> {
                    val refField = wrapper.getReference<Schema>()
                    if (refField.isDefined) {
                        AdditionalPropertiesReference(refField.optional)
                    } else {
                        AdditionalPropertiesSchema(Schema.parseEntity(wrapper).optional)
                    }
                }

                else ->
                    throw NotImplementedError()
            }
        }

        override fun parseWrapper(wrapper: Wrapper): AdditionalProperties {
            return AdditionalProperties(wrapper)
        }
    }
}
