package de.lancom.openapi.common.util

import tools.jackson.databind.JsonNode
import tools.jackson.databind.MapperFeature
import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.SerializationFeature
import tools.jackson.databind.json.JsonMapper
import tools.jackson.dataformat.yaml.YAMLWriteFeature
import tools.jackson.dataformat.yaml.YAMLMapper
import tools.jackson.module.kotlin.kotlinModule

val jsonMapper = createObjectMapper(yaml = false)
val jsonPrettyPrintMapper = createObjectMapper(yaml = false, prettyPrint = true)
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
            node.propertyNames()
                .asSequence()
                .toList()
                .sorted()
                .fold(objectMapper.createObjectNode()) { sorted, key ->
                    sorted.set(key, deepSortJsonNode(node.get(key), objectMapper))
                }

        node.isArray ->
            node.values()
                .asSequence()
                .toList()
                .fold(objectMapper.createArrayNode()) { sorted, entry: JsonNode ->
                    sorted.add(deepSortJsonNode(entry, objectMapper))
                }

        else ->
            node
    }
}

fun createObjectMapper(yaml: Boolean, prettyPrint: Boolean = false): ObjectMapper {
    return if (yaml) {
        val builder = YAMLMapper.builder()
            .addModule(kotlinModule { })
            .disable(YAMLWriteFeature.WRITE_DOC_START_MARKER)
            .enable(YAMLWriteFeature.MINIMIZE_QUOTES)
            .enable(YAMLWriteFeature.SPLIT_LINES)
            .enable(YAMLWriteFeature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        if (prettyPrint) {
            builder.enable(SerializationFeature.INDENT_OUTPUT)
        }
        builder.build()
    } else {
        val builder = JsonMapper.builder()
            .addModule(kotlinModule { })
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        if (prettyPrint) {
            builder.enable(SerializationFeature.INDENT_OUTPUT)
        }
        builder.build()
    }
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
