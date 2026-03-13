package de.lancom.openapi.common.types

import tools.jackson.databind.annotation.JsonDeserialize
import tools.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = TagRef.Companion.KeySerializer::class)
@JsonDeserialize(using = TagRef.Companion.Deserializer::class)
data class TagRef(
    val tag: String
) : JsonWrapper {
    override val key: String = tag

    companion object : JsonWrapperCompanion<TagRef>(::TagRef, TagRef::class.java) {
        class Serializer : AbstractSerializer()
        class KeySerializer : AbstractKeySerializer()
        class Deserializer : AbstractDeserializer()
    }
}
