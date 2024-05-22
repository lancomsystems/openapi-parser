package de.lancom.openapi.common.converter.simple

fun de.lancom.openapi.parser.entity.Responses?.toSimple(
): de.lancom.openapi.simple.entity.Responses {
    return de.lancom.openapi.simple.entity.Responses(
        responses = this?.responses.toSimple(::toSimple),
        extensions = this?.extensions.toSimple(),
    )
}
