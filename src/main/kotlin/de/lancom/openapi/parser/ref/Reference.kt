package de.lancom.openapi.parser.ref

import tools.jackson.core.JsonGenerator
import tools.jackson.core.JsonParser
import tools.jackson.databind.DeserializationContext
import tools.jackson.databind.JsonNode
import tools.jackson.databind.SerializationContext
import tools.jackson.databind.annotation.JsonDeserialize
import tools.jackson.databind.annotation.JsonSerialize
import tools.jackson.databind.deser.std.StdDeserializer
import tools.jackson.databind.ser.std.StdSerializer
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.jackson.Wrapper

@JsonSerialize(using = Reference.Companion.Serializer::class)
@JsonDeserialize(using = Reference.Companion.Deserializer::class)
data class Reference<R : Referenceable>(
    val _ref: Field<String?>,
) : ReferenceOrInstance<R> {
    val ref: String? = _ref.orNull

    val parsedReference: ParsedReference
        get() = ParsedReference(ref)

    companion object {
        class Serializer : StdSerializer<Reference<*>>(Reference::class.java) {
            override fun serialize(value: Reference<*>, gen: JsonGenerator, provider: SerializationContext) {
                gen.writePOJO(
                    mapOf(
                        "\$ref" to value.ref,
                    ),
                )
            }
        }

        class Deserializer : StdDeserializer<Reference<*>>(Reference::class.java) {
            override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Reference<*> {
                val node: JsonNode = p.readValueAsTree()
                val wrapper = Wrapper(node)
                val ref = wrapper["\$ref"].getString().getOrError()
                return Reference<Referenceable>(ref)
            }
        }

        operator fun <R : Referenceable> invoke(ref: String): Reference<R> {
            return Reference(Field(ref))
        }
    }
}
