package de.lancom.openapi.common.converter.simple

import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.treeToValue
import java.io.File

fun ObjectMapper.toSimpleOpenApi(
    treeNode: TreeNode,
): de.lancom.openapi.simple.entity.OpenApi {
    val entity: de.lancom.openapi.parser.entity.OpenApi = treeToValue(treeNode)
    return toSimple(entity)
}

fun ObjectMapper.toSimple(
    file: File,
): de.lancom.openapi.simple.entity.OpenApi {
    val entity: de.lancom.openapi.parser.entity.OpenApi = readValue(file)
    return toSimple(entity)
}

fun ObjectMapper.toSimple(
    content: String,
): de.lancom.openapi.simple.entity.OpenApi {
    val entity: de.lancom.openapi.parser.entity.OpenApi = readValue(content)
    return toSimple(entity)
}
