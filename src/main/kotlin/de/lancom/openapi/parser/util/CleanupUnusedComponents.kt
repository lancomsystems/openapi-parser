package de.lancom.openapi.parser.util

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.common.util.ValidParsedReference
import de.lancom.openapi.parser.entity.Components
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

tailrec fun OpenApi.cleanupUnusedComponents(references: Set<ParsedReference> = emptySet()): OpenApi {
    val updated = filterComponents(references)
    val updatedReferences = updated.entityDescriptor.parsedReferences
    return if (references == updatedReferences) {
        updated
    } else {
        cleanupUnusedComponents(updatedReferences)
    }
}

fun OpenApi.filterComponents(references: Set<ParsedReference>): OpenApi {
    return this
        .updateComponents { components ->
            components?.filterComponents(references)
        }
}

fun Components.filterComponents(references: Set<ParsedReference>): Components {
    return this
        .updateSchemasField { it.filterComponents(ComponentType.Schemas, references) }
        .updateResponsesField { it.filterComponents(ComponentType.Responses, references) }
        .updateParametersField { it.filterComponents(ComponentType.Parameters, references) }
        .updateExamplesField { it.filterComponents(ComponentType.Examples, references) }
        .updateRequestBodiesField { it.filterComponents(ComponentType.RequestBodies, references) }
        .updateHeadersField { it.filterComponents(ComponentType.Headers, references) }
        .updateSecuritySchemesField { it.filterComponents(ComponentType.SecuritySchemes, references) }
        .updateLinksField { it.filterComponents(ComponentType.Links, references) }
        .updateCallbacksField { it.filterComponents(ComponentType.Callbacks, references) }
}

fun <R : Referenceable> Field<Map<String, ReferenceOrInstance<R>?>?>.filterComponents(
    componentType: ComponentType,
    references: Set<ParsedReference>,
): Field<Map<String, ReferenceOrInstance<R>?>?> {
    return flatMap { map ->
        Field.unsetIfNull(
            map?.filterKeys { name: String ->
                references.contains(ValidParsedReference(componentType, name))
            }?.takeIf { it.isNotEmpty() }
        )
    }
}
