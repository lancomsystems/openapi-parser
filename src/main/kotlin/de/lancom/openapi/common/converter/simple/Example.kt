package de.lancom.openapi.common.converter.simple

fun toSimple(
    example: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Example>
): de.lancom.openapi.simple.entity.ExampleOrRef {
    return toSimple(
        example,
        ::toSimple,
        de.lancom.openapi.simple.entity.Example::fromReference,
    )
}

fun toSimple(
    example: de.lancom.openapi.parser.entity.Example,
): de.lancom.openapi.simple.entity.Example {
    return de.lancom.openapi.simple.entity.Example(
        summary = example.summary,
        description = example.description,
        value = example.value.toSimple(),
        externalValue = example.externalValue,
        extensions = example.extensions.toSimple(),
    )
}
