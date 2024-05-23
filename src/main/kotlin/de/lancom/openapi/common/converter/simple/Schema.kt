package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.common.types.SchemaFormat

fun toSimple(
    schema: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Schema>
): de.lancom.openapi.simple.entity.SchemaOrRef {
    return toSimple(
        schema,
        de.lancom.openapi.parser.entity.Schema::toSimple,
        de.lancom.openapi.simple.entity.Schema::fromReference,
    )
}

fun de.lancom.openapi.parser.entity.Schema.toSimple(
): de.lancom.openapi.simple.entity.Schema {
    return de.lancom.openapi.simple.entity.Schema(
        title = title,
        multipleOf = multipleOf,
        maximum = maximum,
        exclusiveMaximum = exclusiveMaximum,
        minimum = minimum,
        exclusiveMinimum = exclusiveMinimum,
        maxLength = maxLength,
        minLength = minLength,
        pattern = pattern,
        maxItems = maxItems,
        minItems = minItems,
        uniqueItems = uniqueItems,
        maxProperties = maxProperties,
        minProperties = minProperties,
        required = required?.filterNotNull()?.toSet() ?: emptySet(),
        enum = enum?.filterNotNull()?.toSet() ?: emptySet(),
        type = type,
        not = not?.let(::toSimple),
        allOf = allOf.toSimple(::toSimple),
        oneOf = oneOf.toSimple(::toSimple),
        anyOf = anyOf.toSimple(::toSimple),
        items = items?.let(::toSimple),
        properties = properties.toSimple(::toSimple),
        additionalProperties = toSimple(additionalProperties),
        description = description,
        format = format?.let(::SchemaFormat),
        default = default.toSimple(),
        nullable = nullable,
        discriminator = discriminator.toSimple(),
        readOnly = readOnly,
        writeOnly = writeOnly,
        example = example.toSimple(),
        externalDocs = externalDocs.toSimple(),
        deprecated = deprecated,
        xml = xml.toSimple(),
        extensions = extensions.toSimple(),
    )
}
