package de.lancom.openapi.common.converter.simple

fun toSimple(
    parameter: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.RequestBody>
): de.lancom.openapi.simple.entity.RequestBodyOrRef {
    return toSimple(
        parameter,
        ::toSimple,
        de.lancom.openapi.simple.entity.RequestBody::fromReference,
    )
}

fun toSimple(
    requestBody: de.lancom.openapi.parser.entity.RequestBody,
): de.lancom.openapi.simple.entity.RequestBody {
    return de.lancom.openapi.simple.entity.RequestBody(
        description = requestBody.description,
        content = requestBody.content.toSimple(),
        required = requestBody.required,
        extensions = requestBody.extensions.toSimple(),
    )
}
