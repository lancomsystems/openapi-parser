package de.lancom.openapi.common.converter.simple

fun toSimple(
    response: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Response>
): de.lancom.openapi.simple.entity.ResponseOrRef {
    return toSimple(
        response,
        ::toSimple,
        de.lancom.openapi.simple.entity.Response::fromReference,
    )
}

fun toSimple(
    response: de.lancom.openapi.parser.entity.Response,
): de.lancom.openapi.simple.entity.Response {
    return de.lancom.openapi.simple.entity.Response(
        description = response.description,
        headers = response.headers.toSimple(::toSimple),
        content = response.content.toSimple(),
        links = response.links.toSimple(::toSimple),
        extensions = response.extensions.toSimple(),
    )
}
