package de.lancom.openapi.common.types

import tools.jackson.databind.annotation.JsonDeserialize
import tools.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = SchemaFormat.Companion.KeySerializer::class)
@JsonDeserialize(using = SchemaFormat.Companion.Deserializer::class)
data class SchemaFormat(val format: String) : JsonWrapper {
    override val key: String = format

    companion object : JsonWrapperCompanion<SchemaFormat>(::SchemaFormat, SchemaFormat::class.java) {
        class Serializer : AbstractSerializer()
        class KeySerializer : AbstractKeySerializer()
        class Deserializer : AbstractDeserializer()

        val UUID = SchemaFormat("uuid")
        val INT32 = SchemaFormat("int32")
        val INT64 = SchemaFormat("int64")
        val FLOAT = SchemaFormat("float")
        val DATE = SchemaFormat("date")
        val DATE_TIME = SchemaFormat("date-time")
        val EMAIL = SchemaFormat("email")
        val BINARY = SchemaFormat("binary")
    }
}
