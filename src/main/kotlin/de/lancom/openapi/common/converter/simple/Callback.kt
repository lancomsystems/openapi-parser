package de.lancom.openapi.common.converter.simple

fun toSimple(
    callback: de.lancom.openapi.parser.ref.ReferenceOrInstance<de.lancom.openapi.parser.entity.Callback>
): de.lancom.openapi.simple.entity.CallbackOrRef {
    return toSimple(
        callback,
        ::toSimple,
        de.lancom.openapi.simple.entity.Callback::fromReference,
    )
}

fun toSimple(
    callback: de.lancom.openapi.parser.entity.Callback,
): de.lancom.openapi.simple.entity.Callback {
    return de.lancom.openapi.simple.entity.Callback(
        pathItems = callback.pathItems.toSimple(::toSimple),
        extensions = callback.extensions.toSimple(),
    )
}
