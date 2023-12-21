package de.lancom.openapi.tools

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import de.lancom.openapi.jackson.createObjectMapper

val jsonMapper = createObjectMapper(yaml = false)
val yamlMapper = createObjectMapper(yaml = true)

fun Any?.toJsonNode(): JsonNode {
    return yamlMapper.valueToTree(this)
}

fun Any?.toYamlString(): String {
    return toJsonNode().toYamlString()
}

fun JsonNode.toYamlString(): String {
    return yamlMapper.writeValueAsString(deepSortJsonNode(this, jsonMapper))
}

fun deepSortJsonNode(node: JsonNode, objectMapper: ObjectMapper): JsonNode {
    return when {
        node.isObject ->
            node.fieldNames()
                .asSequence()
                .toList()
                .sorted()
                .fold(objectMapper.createObjectNode()) { sorted, key ->
                    sorted.set(key, deepSortJsonNode(node.get(key), objectMapper))
                }

        node.isArray ->
            node.elements()
                .asSequence()
                .toList()
                .fold(objectMapper.createArrayNode()) { sorted, entry: JsonNode ->
                    sorted.add(deepSortJsonNode(entry, objectMapper))
                }

        else ->
            node
    }
}
