package de.lancom.openapi.common.converter.simple

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.BooleanNode
import com.fasterxml.jackson.databind.node.TextNode
import de.lancom.openapi.parser.entity.RawExtension

fun toSimple(
    extension: de.lancom.openapi.parser.entity.Extension,
): de.lancom.openapi.simple.types.Extension {
    return if (extension is RawExtension) {
        toSimple(extension.jsonNode)
    } else {
        de.lancom.openapi.simple.types.Extension()
    }
}

fun toSimple(
    jsonNode: JsonNode,
): de.lancom.openapi.simple.types.Extension {
    return when (jsonNode) {
        is BooleanNode ->
            de.lancom.openapi.simple.types.Extension(jsonNode.booleanValue())

        is TextNode ->
            de.lancom.openapi.simple.types.Extension(jsonNode.textValue())

        is ArrayNode ->
            de.lancom.openapi.simple.types.Extension(
                jsonNode
                    .elements()
                    .asSequence()
                    .map(::toSimple)
                    .map(de.lancom.openapi.simple.types.Extension::value)
                    .toList()
            )

        else ->
            de.lancom.openapi.simple.types.Extension()
    }
}

fun Map<String, de.lancom.openapi.parser.entity.Extension?>?.toSimple(
): Map<String, de.lancom.openapi.simple.types.Extension> {
    return toSimple(::toSimple)
}
