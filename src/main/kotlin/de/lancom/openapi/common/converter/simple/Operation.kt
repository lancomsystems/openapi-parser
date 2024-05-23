package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.common.types.TagRef

fun de.lancom.openapi.parser.entity.Operation.toSimple(
): de.lancom.openapi.simple.entity.Operation {
    return de.lancom.openapi.simple.entity.Operation(
        tags = tags?.filterNotNull()?.map(::TagRef) ?: emptyList(),
        summary = summary,
        description = description,
        externalDocs = externalDocs.toSimple(),
        operationId = operationId,
        parameters = parameters.toSimple(::toSimple),
        requestBody = requestBody?.let(::toSimple),
        responses = responses.toSimple(),
        callbacks = callbacks.toSimple(::toSimple),
        deprecated = deprecated,
        security = security.toSimple(),
        servers = servers.toSimple(::toSimple),
        extensions = extensions.toSimple(),
    )
}
