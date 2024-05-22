package de.lancom.openapi.common.converter.simple

fun toSimple(
    openApi: de.lancom.openapi.parser.entity.OpenApi,
): de.lancom.openapi.simple.entity.OpenApi {
    return de.lancom.openapi.simple.entity.OpenApi(
        openapi = openApi.openapi,
        info = toSimple(openApi.info),
        externalDocs = openApi.externalDocs.toSimple(),
        servers = openApi.servers.toSimple(::toSimple),
        security = openApi.security.toSimple(),
        tags = openApi.tags.toSimple(),
        paths = toSimple(openApi.paths),
        components = toSimple(openApi.components),
        extensions = openApi.extensions.toSimple(),
    )
}
