package de.lancom.openapi.common.converter.simple

fun toSimple(
    encoding: de.lancom.openapi.parser.entity.Encoding,
): de.lancom.openapi.simple.entity.Encoding {
    return de.lancom.openapi.simple.entity.Encoding(
        contentType = encoding.contentType,
        headers = encoding.headers.toSimple(::toSimple),
        style = encoding.style,
        explode = encoding.explode,
        allowReserved = encoding.allowReserved,
        extensions = encoding.extensions.toSimple(),
    )
}
