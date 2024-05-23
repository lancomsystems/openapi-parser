package de.lancom.openapi.common.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper

val jsonMapper = createObjectMapper(yaml = false)
val jsonPrettyPrintMapper = createObjectMapper(yaml = false).enable(SerializationFeature.INDENT_OUTPUT)
val yamlMapper = createObjectMapper(yaml = true)

fun <T : Any> T.toJsonNode(): JsonNode {
    return yamlMapper.valueToTree(this)
}

fun <T : Any> T.toYamlString(ignoreOrder: Boolean = true): String {
    return toJsonNode().toYamlString(ignoreOrder = ignoreOrder)
}

fun JsonNode.toYamlString(ignoreOrder: Boolean = true): String {
    val node = if (ignoreOrder) {
        deepSortJsonNode(this, jsonMapper)
    } else {
        this
    }
    return yamlMapper.writeValueAsString(node)
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

fun createObjectMapper(yaml: Boolean): ObjectMapper {
    val mapperBuilder = if (yaml) {
        YAMLMapper.builder()
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.SPLIT_LINES)
            .enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS)
    } else {
        JsonMapper.builder()
    }
    return mapperBuilder
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .build()
}

fun JsonNode.booleanValueOrErrorOpt(): Boolean? {
    if (isNull) {
        return null
    }
    return booleanValueOrError()
}

fun JsonNode.booleanValueOrError(): Boolean {
    require(isBoolean) {
        "a boolean node is required"
    }
    return booleanValue()
}

fun JsonNode.numberValueOrError(): Number {
    require(isNumber) {
        "a number node is required"
    }
    return numberValue()
}

fun JsonNode.textValueOrError(): String {
    require(isTextual) {
        "a textural node is required"
    }
    return textValue()
}

fun JsonNode.textValueOrErrorOpt(): String? {
    if (isNull) {
        return null
    }
    return textValueOrError()
}
