package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = TagRef.Companion.Serializer::class)
@JsonDeserialize(using = TagRef.Companion.Deserializer::class)
data class TagRef(
    val tag: String
) : JsonWrapper {
    override val key: String = tag

    companion object : JsonWrapperCompanion<TagRef>(::TagRef, TagRef::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()
    }
}
