package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.common.types.ContentType

fun Map<String, de.lancom.openapi.parser.entity.MediaType?>?.toSimple(
): Map<ContentType, de.lancom.openapi.simple.entity.MediaType> {
    return toSimple(::toSimple).mapKeys { (contentType, _) ->
        ContentType(contentType)
    }
}

fun toSimple(
    mediaType: de.lancom.openapi.parser.entity.MediaType
): de.lancom.openapi.simple.entity.MediaType {
    return de.lancom.openapi.simple.entity.MediaType(
        schema = mediaType.schema?.let(::toSimple),
        example = mediaType.example.toSimple(),
        examples = mediaType.examples.toSimple(::toSimple),
        encoding = mediaType.encoding.toSimple(::toSimple),
        extensions = mediaType.extensions.toSimple(),
    )
}
