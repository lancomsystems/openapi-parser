package de.lancom.openapi.common.converter.simple

fun toSimple(
    info: de.lancom.openapi.parser.entity.Info?,
): de.lancom.openapi.simple.entity.Info {
    return de.lancom.openapi.simple.entity.Info(
        title = info?.title,
        description = info?.description,
        termsOfService = info?.termsOfService,
        contact = info?.contact?.let(::toSimple),
        license = info?.license?.let(::toSimple),
        version = info?.version,
        extensions = info?.extensions.toSimple(),
    )
}
