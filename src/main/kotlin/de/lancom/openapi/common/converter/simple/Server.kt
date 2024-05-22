package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.parser.entity.ServerVariable

fun toSimple(
    server: de.lancom.openapi.parser.entity.Server,
): de.lancom.openapi.simple.entity.Server {
    return de.lancom.openapi.simple.entity.Server(
        url = server.url,
        description = server.description,
        variables = server.variables.toSimple(ServerVariable::toSimple),
        extensions = server.extensions.toSimple(),
    )
}
