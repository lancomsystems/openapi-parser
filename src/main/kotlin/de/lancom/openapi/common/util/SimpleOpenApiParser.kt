package de.lancom.openapi.common.util

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.converter.simple.toSimple
import java.io.File

fun simpleOpenApiParser(file: File): de.lancom.openapi.simple.entity.OpenApi {
    val parsedOpenApi: de.lancom.openapi.parser.entity.OpenApi = yamlMapper.readValue(file)
    return toSimple(parsedOpenApi)
}
