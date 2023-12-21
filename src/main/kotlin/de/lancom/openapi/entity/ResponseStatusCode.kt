package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.jackson.Key
import de.lancom.openapi.jackson.KeyDeserializer
import de.lancom.openapi.jackson.KeySerializer

@JsonDeserialize(keyUsing = ResponseStatusCode.Companion.Deserializer::class)
@JsonSerialize(keyUsing = ResponseStatusCode.Companion.Serializer::class)
data class ResponseStatusCode(
    val code: String
) : Key {
    override val key: String = code

    constructor(code: Int) : this(code.toString())

    companion object {
        class Deserializer : KeyDeserializer<ResponseStatusCode>(::ResponseStatusCode)
        class Serializer : KeySerializer<ResponseStatusCode>(ResponseStatusCode::class.java)

        val DEFAULT = ResponseStatusCode("default")
        val HTTP_200_OK = ResponseStatusCode(200)
        val HTTP_204_NO_CONTENT = ResponseStatusCode(204)
    }
}
