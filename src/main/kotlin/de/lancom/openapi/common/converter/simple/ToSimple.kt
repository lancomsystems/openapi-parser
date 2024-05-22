package de.lancom.openapi.common.converter.simple

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.parser.entity.JsonEntity

fun JsonEntity?.toSimple(
): JsonNode? {
    return this?.jsonNode
}

fun List<JsonEntity?>?.toSimple(
): List<JsonNode> {
    return (this ?: emptyList()).toList().filterNotNull().map(JsonEntity::jsonNode)
}

fun <In, Out> List<In?>?.toSimple(
    toSimple: (In) -> Out,
): List<Out> {
    return (this ?: emptyList()).toList().filterNotNull().map(toSimple)
}

fun <Key, In, Out> Map<Key, In?>?.toSimple(
    toSimple: (In) -> Out,
): Map<Key, Out> {
    return (this ?: emptyMap()).mapNotNull { (key, value) ->
        value?.let { key to toSimple(it) }
    }.toMap()
}

fun <KeyIn, KeyOut, In, Out> Map<KeyIn, In?>?.toSimple(
    toSimple: (In) -> Out,
    keyMapper: (KeyIn) -> KeyOut,
): Map<KeyOut, Out> {
    return (this ?: emptyMap()).mapNotNull { (key, value) ->
        value?.let { keyMapper(key) to toSimple(it) }
    }.toMap()
}

fun Map<String, String?>?.toSimple(
): Map<String, String> {
    return (this ?: emptyMap()).mapNotNull { (key, value) ->
        value?.let { key to value }
    }.toMap()
}

inline fun <In : de.lancom.openapi.parser.ref.Referenceable, Out : de.lancom.openapi.simple.entity.ComponentOrRef> toSimple(
    referenceOrInstance: de.lancom.openapi.parser.ref.ReferenceOrInstance<In>,
    toSimple: (In) -> Out,
    toSimpleRef: (ParsedReference) -> Out,
): Out {
    return when (referenceOrInstance) {
        is de.lancom.openapi.parser.ref.Instance<In> ->
            toSimple(referenceOrInstance.referenced)

        is de.lancom.openapi.parser.ref.Reference<In> ->
            toSimpleRef(
                referenceOrInstance.parsedReference,
            )
    }
}

fun <R : de.lancom.openapi.parser.ref.Referenceable> toSimple(
    reference: de.lancom.openapi.parser.ref.Reference<R>?
): de.lancom.openapi.simple.entity.Reference? {
    return reference?.ref?.let(de.lancom.openapi.simple.entity.Reference::invoke)
}
