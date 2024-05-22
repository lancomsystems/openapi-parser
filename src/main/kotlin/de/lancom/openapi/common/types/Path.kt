package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = Path.Companion.Serializer::class)
@JsonDeserialize(using = Path.Companion.Deserializer::class)
data class Path(
    val path: String
) : JsonWrapper {
    override val key: String = path

    companion object : JsonWrapperCompanion<Path>(::Path, Path::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()
    }
}
