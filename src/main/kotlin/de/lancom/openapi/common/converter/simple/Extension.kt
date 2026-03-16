package de.lancom.openapi.common.converter.simple

import tools.jackson.databind.JsonNode
import tools.jackson.databind.node.ArrayNode
import tools.jackson.databind.node.BooleanNode
import tools.jackson.databind.node.StringNode
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

        is StringNode ->
            de.lancom.openapi.simple.types.Extension(jsonNode.textValue())

        is ArrayNode ->
            de.lancom.openapi.simple.types.Extension(
                jsonNode
                    .values()
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
