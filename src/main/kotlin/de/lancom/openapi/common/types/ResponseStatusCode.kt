package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(
    using = ResponseStatusCode.Companion.Serializer::class,
    keyUsing = ResponseStatusCode.Companion.KeySerializer::class,
)
@JsonDeserialize(using = ResponseStatusCode.Companion.Deserializer::class)
data class ResponseStatusCode(
    val code: String
) : JsonWrapper {
    override val key: String = code

    constructor(code: Int) : this(code.toString())

    companion object : JsonWrapperCompanion<ResponseStatusCode>(::ResponseStatusCode, ResponseStatusCode::class.java) {
        class Serializer : AbstractSerializer()
        class KeySerializer : AbstractKeySerializer()
        class Deserializer : AbstractDeserializer()

        val DEFAULT = ResponseStatusCode("default")
        val HTTP_200_OK = ResponseStatusCode(200)
        val HTTP_204_NO_CONTENT = ResponseStatusCode(204)
    }
}
