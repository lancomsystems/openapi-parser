package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.common.types.In
import de.lancom.openapi.common.types.ParameterStyle

fun toSimple(
    parameter: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Parameter>
): de.lancom.openapi.simple.entity.ParameterOrRef {
    return toSimple(
        parameter,
        ::toSimple,
        de.lancom.openapi.simple.entity.Parameter::fromReference,
    )
}

fun toSimple(
    parameter: de.lancom.openapi.parser.entity.Parameter,
): de.lancom.openapi.simple.entity.Parameter {
    return de.lancom.openapi.simple.entity.Parameter(
        name = parameter.name,
        `in` = In.valueOf(parameter.`in`?.name!!),
        description = parameter.description,
        required = parameter.required,
        deprecated = parameter.deprecated,
        allowEmptyValue = parameter.allowEmptyValue,
        style = parameter.style?.let(ParameterStyle::fromString) ?: ParameterStyle.Default,
        explode = parameter.explode,
        allowReserved = parameter.allowReserved,
        schema = parameter.schema?.let(::toSimple),
        example = parameter.example.toSimple(),
        examples = parameter.examples.toSimple(::toSimple),
        content = parameter.content.toSimple(::toSimple),
        extensions = parameter.extensions.toSimple(),
    )
}
