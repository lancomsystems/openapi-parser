package de.lancom.openapi.common.converter.simple

fun toSimple(
    parameter: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Header>
): de.lancom.openapi.simple.entity.HeaderOrRef {
    return toSimple(
        parameter,
        ::toSimple,
        de.lancom.openapi.simple.entity.Header::fromReference,
    )
}

fun toSimple(
    header: de.lancom.openapi.parser.entity.Header,
): de.lancom.openapi.simple.entity.Header {
    return de.lancom.openapi.simple.entity.Header(
        description = header.description,
        required = header.required,
        deprecated = header.deprecated,
        allowEmptyValue = header.allowEmptyValue,
        style = header.style,
        explode = header.explode,
        allowReserved = header.allowReserved,
        schema = header.schema?.let(::toSimple),
        example = header.example.toSimple(),
        examples = header.examples.toSimple(::toSimple),
        content = header.content.toSimple(::toSimple),
        extensions = header.extensions.toSimple(),
    )
}
