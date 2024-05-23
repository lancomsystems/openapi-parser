package de.lancom.openapi.common.converter.simple

import de.lancom.openapi.simple.entity.*

fun toSimple(
    components: de.lancom.openapi.parser.entity.Components?,
): de.lancom.openapi.simple.entity.Components {
    return de.lancom.openapi.simple.entity.Components(
        schemas = components?.schemas.toSimple(::toSimple, SchemaReference::fromName)
            .addComponentReferences(Schema::addComponentReference),
        responses = components?.responses.toSimple(::toSimple, ResponseReference::fromName)
            .addComponentReferences(Response::addComponentReference),
        parameters = components?.parameters.toSimple(::toSimple, ParameterReference::fromName),
        examples = components?.examples.toSimple(::toSimple, ExampleReference::fromName),
        requestBodies = components?.requestBodies.toSimple(::toSimple, RequestBodyReference::fromName),
        headers = components?.headers.toSimple(::toSimple, HeaderReference::fromName),
        securitySchemes = components?.securitySchemes.toSimple(::toSimple, SecuritySchemeReference::fromName),
        links = components?.links.toSimple(::toSimple, LinkReference::fromName),
        callbacks = components?.callbacks.toSimple(::toSimple, CallbackReference::fromName),
        extensions = components?.extensions.toSimple(),
    )
}

private inline fun <OrRef : ComponentOrRef, R : Reference, reified C : Component<OrRef, R>> Map<R, OrRef>.addComponentReferences(
    crossinline addComponentReference: (C, R) -> OrRef,
): Map<R, OrRef> {
    return mapValues { (reference, orRef: OrRef) ->
        when (orRef) {
            is C ->
                addComponentReference(orRef, reference)

            is SchemaReference ->
                orRef

            else ->
                throw IllegalArgumentException("unexpected component type: $orRef")
        }
    }
}
