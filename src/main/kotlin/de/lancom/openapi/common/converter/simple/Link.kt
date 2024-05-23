package de.lancom.openapi.common.converter.simple

fun toSimple(
    link: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Link>
): de.lancom.openapi.simple.entity.LinkOrRef {
    return toSimple(
        link,
        ::toSimple,
        de.lancom.openapi.simple.entity.Link::fromReference,
    )
}

fun toSimple(
    link: de.lancom.openapi.parser.entity.Link,
): de.lancom.openapi.simple.entity.Link {
    return de.lancom.openapi.simple.entity.Link(
        operationId = link.operationId,
        operationRef = link.operationRef,
        parameters = link.parameters.toSimple(),
        requestBody = link.requestBody.toSimple(),
        description = link.description,
        server = link.server?.let(::toSimple),
        extensions = link.extensions.toSimple(),
    )
}
