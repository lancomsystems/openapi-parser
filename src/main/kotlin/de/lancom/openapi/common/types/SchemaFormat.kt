package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

@JsonSerialize(keyUsing = SchemaFormat.Companion.Serializer::class)
@JsonDeserialize(using = SchemaFormat.Companion.Deserializer::class)
data class SchemaFormat(val format: String) : JsonWrapper {
    override val key: String = format

    companion object : JsonWrapperCompanion<SchemaFormat>(::SchemaFormat, SchemaFormat::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()

        val UUID = SchemaFormat("uuid")
        val INT32 = SchemaFormat("int32")
        val INT64 = SchemaFormat("int64")
        val DATE = SchemaFormat("date")
        val DATE_TIME = SchemaFormat("date-time")
        val EMAIL = SchemaFormat("email")
    }
}
