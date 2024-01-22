package de.lancom.openapi.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper

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

fun <C : Collection<C>> C?.takeUnlessEmpty(): C? {
    return if (isNullOrEmpty()) {
        null
    } else {
        this
    }
}

fun <K, V> Map<K, V>?.takeUnlessEmpty(): Map<K, V>? {
    return if (isNullOrEmpty()) {
        null
    } else {
        this
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
