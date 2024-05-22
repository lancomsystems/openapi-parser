package de.lancom.openapi.common.converter.simple

fun toSimple(
    contact: de.lancom.openapi.parser.entity.Contact,
): de.lancom.openapi.simple.entity.Contact {
    return de.lancom.openapi.simple.entity.Contact(
        name = contact.name,
        url = contact.url,
        email = contact.email,
        extensions = contact.extensions.toSimple(),
    )
}
