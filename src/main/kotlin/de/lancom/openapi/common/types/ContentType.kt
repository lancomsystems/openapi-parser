package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(using = ContentType.Companion.Serializer::class)
@JsonDeserialize(using = ContentType.Companion.Deserializer::class)
data class ContentType(
    val contentType: String
) : JsonWrapper {
    override val key: String = contentType

    companion object : JsonWrapperCompanion<ContentType>(::ContentType, ContentType::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()

        val applicationJson = ContentType("application/json")
    }
}
