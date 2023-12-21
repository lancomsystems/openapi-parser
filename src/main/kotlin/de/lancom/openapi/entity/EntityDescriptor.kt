package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.field.Field
import de.lancom.openapi.field.getFields
import de.lancom.openapi.refs.Reference

tailrec fun Any?.flatten(): List<Any?> {
    val flat = when (this) {
        null ->
            emptyList()

        is List<Any?> ->
            flatMap { entry ->
                @Suppress("NON_TAIL_RECURSIVE_CALL")
                entry.flatten()
            }

        is Map<*, *> ->
            values.toList()

        is Collection<*> ->
            toList()

        is Iterable<*> ->
            toList()

        is Iterator<*> ->
            asSequence().toList()

        is Sequence<*> ->
            toList()

        is Field<*> ->
            if (isDefined) {
                listOf(orNull)
            } else {
                emptyList()
            }

        else ->
            return listOf(this)
    }

    return if (flat == this || flat.isEmpty()) {
        flat
    } else {
        flat.flatten()
    }
}

data class EntityDescriptor(
    val entity: Entity,
    val jsonNode: Field<JsonNode>?,
    val map: Map<String, Field<out Any?>>,
    val flatMap: List<Field<out Map<out Any, Any?>?>>,
    val flatten: List<Field<out Any?>>,
) {
    val entityMap: Map<Any, Any?> by lazy {
        val jsonMap: Map<out Any, Any?> = map.getFields()
        val jsonMapFlat: Map<Any, Any?> = flatMap.getFields()
            .filterNotNull()
            .flatMap(Map<out Any, Any?>::toList)
            .toMap()
        val flattenMap: Map<out Any, Any?> = flatten
            .getFields()
            .filterIsInstance<Map<out Any, *>>()
            .flatMap(Map<out Any, *>::toList)
            .toMap()
        jsonMap + jsonMapFlat + flattenMap
    }

    val isFlat: Boolean by lazy {
        val f = flatten.flatten()
        f.isNotEmpty() && !f.any { any ->
            any is Map<*, *> || any is Entity
        }
    }

    val subEntities: Set<Entity> by lazy {
        listOf(
            map,
            flatMap,
            flatten,
        ).flatten().filterIsInstance<Entity>().toSet()
    }

    val subEntitiesRecursive: Set<Entity> by lazy {
        setOf(entity) + subEntities.flatMap { entity ->
            entity.entityDescriptor.subEntitiesRecursive
        }.toSet()
    }

    val references: Set<Reference<*>> by lazy {
        subEntitiesRecursive.filterIsInstance<Reference<*>>().toSet()
    }
}
