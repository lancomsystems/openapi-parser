package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.jackson.Key
import de.lancom.openapi.jackson.KeyDeserializer
import de.lancom.openapi.jackson.KeySerializer

@JsonDeserialize(keyUsing = ContentType.Companion.Deserializer::class)
@JsonSerialize(keyUsing = ContentType.Companion.Serializer::class)
data class ContentType(val contentType: String) : Key {
    override val key: String = contentType

    companion object {
        class Deserializer : KeyDeserializer<ContentType>(::ContentType)
        class Serializer : KeySerializer<ContentType>(ContentType::class.java)

        val applicationJson = ContentType("application/json")
    }
}
