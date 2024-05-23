package de.lancom.openapi.parser.ref

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
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
            override fun serialize(value: Reference<*>?, gen: JsonGenerator?, provider: SerializerProvider?) {
                gen!!.writeObject(
                    mapOf(
                        "\$ref" to value!!.ref
                    )
                )
            }
        }

        class Deserializer : StdDeserializer<Reference<*>>(Reference::class.java) {
            override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Reference<*> {
                val node: JsonNode = p!!.codec!!.readTree(p)!!
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
