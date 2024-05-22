package de.lancom.openapi.common.converter.simple

fun List<de.lancom.openapi.parser.entity.Tag?>?.toSimple(
): List<de.lancom.openapi.simple.entity.Tag> {
    return (this ?: emptyList()).toList().filterNotNull().map { tag ->
        tag.toSimple()
    }
}

fun de.lancom.openapi.parser.entity.Tag.toSimple(
): de.lancom.openapi.simple.entity.Tag {
    return de.lancom.openapi.simple.entity.Tag(
        name = name,
        description = description,
        externalDocs = externalDocs.toSimple(),
        extensions = extensions.toSimple(),
    )
}
