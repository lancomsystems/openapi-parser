package de.lancom.openapi.common.converter.simple

fun toSimple(
    license: de.lancom.openapi.parser.entity.License,
): de.lancom.openapi.simple.entity.License {
    return de.lancom.openapi.simple.entity.License(
        name = license.name,
        url = license.url,
    )
}
