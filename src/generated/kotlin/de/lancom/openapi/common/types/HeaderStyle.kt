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
@JsonSerialize(using = HeaderStyle.Companion.Serializer::class)
@JsonDeserialize(using = HeaderStyle.Companion.Deserializer::class)
enum class HeaderStyle(
    override val key: kotlin.String,
) : JsonWrapper {
    Simple("simple"),
    ;

    companion object : JsonWrapperCompanion<HeaderStyle>(HeaderStyle::fromString, HeaderStyle::class.java) {
        class Serializer : AbstractSerializer()
        class Deserializer : AbstractDeserializer()

        val byKey: Map<kotlin.String, HeaderStyle> = entries.associateBy(HeaderStyle::key)

        fun fromString(value: kotlin.String): HeaderStyle {
            return byKey[value]
              ?: throw IllegalArgumentException("Unknown HeaderStyle value: $value")
        }
    }
}