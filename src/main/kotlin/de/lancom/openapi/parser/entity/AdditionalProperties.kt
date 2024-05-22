package de.lancom.openapi.parser.entity

import de.lancom.openapi.parser.jackson.EntityDeserializer
import de.lancom.openapi.parser.jackson.EntitySerializer
import de.lancom.openapi.parser.jackson.Parser
import de.lancom.openapi.parser.jackson.Wrapper
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Reference
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

sealed interface AdditionalProperties : Referenceable {
    override val __referenceName: String?
        get() = null

    override fun getReferencePath(): String? {
        TODO("Not yet implemented")
    }

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
