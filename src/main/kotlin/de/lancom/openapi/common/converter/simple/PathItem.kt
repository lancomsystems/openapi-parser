package de.lancom.openapi.common.converter.simple

fun toSimple(
    pathItem: de.lancom.openapi.parser.entity.PathItem,
): de.lancom.openapi.simple.entity.PathItem {
    return de.lancom.openapi.simple.entity.PathItem(
        summary = pathItem.summary,
        description = pathItem.description,
        get = pathItem.get?.toSimple(),
        put = pathItem.put?.toSimple(),
        post = pathItem.post?.toSimple(),
        delete = pathItem.delete?.toSimple(),
        options = pathItem.options?.toSimple(),
        head = pathItem.head?.toSimple(),
        patch = pathItem.patch?.toSimple(),
        trace = pathItem.trace?.toSimple(),
        servers = pathItem.servers.toSimple(::toSimple),
        parameters = pathItem.parameters.toSimple(::toSimple),
        extensions = pathItem.extensions.toSimple(),
    )
}
