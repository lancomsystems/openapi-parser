package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.common.types.Path

fun toSimple(
    paths: de.lancom.openapi.parser.entity.Paths?,
): de.lancom.openapi.simple.entity.Paths {
    return de.lancom.openapi.simple.entity.Paths(
        pathItems = paths?.pathItems.toSimple(::toSimple, ::Path),
        extensions = paths?.extensions.toSimple(),
    )
}
