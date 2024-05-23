package de.lancom.openapi.common.converter.simple

fun de.lancom.openapi.parser.entity.ServerVariable.toSimple(
): de.lancom.openapi.simple.entity.ServerVariable {
    return de.lancom.openapi.simple.entity.ServerVariable(
        enum = enum?.filterNotNull() ?: emptyList(),
        default = default,
        description = description,
        extensions = extensions.toSimple(),
    )
}
