package de.lancom.openapi.parser.util

import de.lancom.openapi.parser.entity.*
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Reference

fun OpenApi.filterRef(reference: Reference<*>): OpenApi {
    val endpoints = filterApiEndpoints { _, _, operation ->
        operation?.entityDescriptor?.references?.contains(reference) ?: false
    }
    val references = endpoints.entityDescriptor.references
    return endpoints.updateComponents { components ->
        components
            ?.updateSchemas(filterRefOptional("schemas", reference, references) { Instance(Schema()) })
            ?.updateResponses(filterRefOptional("responses", reference, references) { Instance(Response()) })
            ?.updateParameters(filterRefOptional("parameters", reference, references) { parameter -> parameter })
            ?.updateExamples(filterRefOptional("examples", reference, references) { Instance(Example()) })
            ?.updateRequestBodies(filterRefOptional("requestBodies", reference, references) { Instance(RequestBody()) })
            ?.updateHeaders(filterRefOptional("headers", reference, references) { Instance(Header()) })
            //?.updateSecuritySchemes(filterRefOptional("securitySchemes", reference, references) { SecurityScheme() })
            ?.updateLinks(filterRefOptional("links", reference, references) { Instance(Link()) })
            ?.updateCallbacks(filterRefOptional("callbacks", reference, references) { Instance(Callback()) })
        // TODO
        //?.updateExtensions(filterRefRequired("extensions", reference, references) { Extension() })
    }
}

fun <V> filterRefOptional(
    type: String,
    reference: Reference<*>,
    references: Set<Reference<*>>,
    empty: (V?) -> V?,
): (Map<String, V?>?) -> Map<String, V?>? {
    val filter: (Map<String, V?>) -> Map<String, V?> = filterRefRequired(type, reference, references, empty)
    return { maybeMap ->
        if (maybeMap == null) {
            null
        } else {
            filter(maybeMap)
        }
    }
}

fun <V> filterRefRequired(
    type: String,
    reference: Reference<*>,
    references: Set<Reference<*>>,
    empty: (V?) -> V?,
): (Map<String, V?>) -> Map<String, V?> {
    return { map ->
        map.toList().mapNotNull { (name, value) ->
            val ref = "#/components/$type/$name"
            if (reference.ref == ref) {
                name to value
            } else if (references.any { reference -> reference.ref == ref } || references.contains(value as Any?)) {
                name to empty(value)
            } else {
                null
            }
        }.toMap()
    }
}
