/*****************************************************************************
**   C A U T I O N                                                          **
**   This file is auto-generated!                                           **
**   If you want to make changes, please see the README.md file.            **
**   Please do not edit this file directly!                                 **
*****************************************************************************/
package de.lancom.openapi.common.types

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.jackson.JsonWrapper
import de.lancom.openapi.common.jackson.JsonWrapperCompanion

// hint:D094ACB1
@JsonSerialize(using = In.Companion.Serializer::class)
@JsonDeserialize(using = In.Companion.Deserializer::class)
enum class In(
    override val key: kotlin.String,
) : JsonWrapper {
    Path("path"),
    Query("query"),
    Header("header"),
    Cookie("cookie"),
    ;

    companion object : JsonWrapperCompanion<In>(In::fromString, In::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()

        val byKey: Map<kotlin.String, In> = entries.associateBy(In::key)

        fun fromString(value: kotlin.String): In {
            return byKey[value]
              ?: throw IllegalArgumentException("Unknown In value: $value")
        }
    }
}
