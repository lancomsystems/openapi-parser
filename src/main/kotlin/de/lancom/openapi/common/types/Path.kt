package de.lancom.openapi.common.types

import tools.jackson.databind.annotation.JsonDeserialize
import tools.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = Path.Companion.KeySerializer::class)
@JsonDeserialize(using = Path.Companion.Deserializer::class)
data class Path(
    val path: String
) : JsonWrapper {
    override val key: String = path

    companion object : JsonWrapperCompanion<Path>(::Path, Path::class.java) {
        class Serializer : AbstractSerializer()
        class KeySerializer : AbstractKeySerializer()
        class Deserializer : AbstractDeserializer()
    }
}
