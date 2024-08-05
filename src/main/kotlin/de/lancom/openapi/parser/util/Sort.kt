package de.lancom.openapi.parser.util

import de.lancom.openapi.parser.entity.Components
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.entity.Paths
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

fun OpenApi.sort(
    openApiFieldOrder: Set<OpenApi.Companion.Fields> = OpenApi.Companion.Fields.all,
    componentsFieldOrder: Set<Components.Companion.Fields> = Components.Companion.Fields.all,
): OpenApi {
    return copy(
        __field_order = openApiFieldOrder,
    )
        .updateFields()
        .updatePaths { paths ->
            paths?.sort()
        }
        .updateComponents { components ->
            components?.sort(componentsFieldOrder)
        }
}

fun Paths.sort(): Paths {
    return updatePathItems { pathItems ->
        pathItems.toList().sortedBy { it.first }.toMap()
    }
        // sort path order by resetting to default field order
        .copy(__field_order = emptySet())
        .updateFields()
}

fun Components.sort(
    componentsFieldOrder: Set<Components.Companion.Fields> = Components.Companion.Fields.all,
): Components {
    return this
        .updateSchemas { it.sort() }
        .updateResponses { it.sort() }
        .updateParameters { it.sort() }
        .updateExamples { it.sort() }
        .updateRequestBodies { it.sort() }
        .updateHeaders { it.sort() }
        .updateSecuritySchemes { it.sort() }
        .updateLinks { it.sort() }
        .updateCallbacks { it.sort() }
        .copy(
            __field_order = componentsFieldOrder,
        )
        .updateFields()
}

fun <R : Referenceable> Map<String, ReferenceOrInstance<R>?>?.sort(): Map<String, ReferenceOrInstance<R>?>? {
    return this
        ?.toList()
        ?.sortedBy { it.first }
        ?.toMap()
}
