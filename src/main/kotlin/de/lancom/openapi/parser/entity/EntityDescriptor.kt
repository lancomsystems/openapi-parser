package de.lancom.openapi.parser.entity

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.field.getFields
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.Reference

private tailrec fun Any?.flattenAny(): List<Any?> {
    val flat = when (this) {
        null ->
            emptyList()

        is List<Any?> ->
            flatMap { entry ->
                @Suppress("NON_TAIL_RECURSIVE_CALL")
                entry.flattenAny()
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
        flat.flattenAny()
    }
}

sealed interface EntityOrReference

data class EntityOrReferenceEntity(
    val entity: Entity,
) : EntityOrReference

data class EntityOrReferenceReference(
    val reference: Reference<*>,
) : EntityOrReference

data class EntityDescriptor(
    val entity: Entity,
    val jsonNode: Field<JsonNode>?,
    val map: Map<String, Field<out Any?>>,
    val flatMap: List<Field<out Map<out Any, Any?>?>>,
    val flatten: List<Field<out Any?>>,
    val order: List<String>,
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
        val f = flatten.flattenAny()
        f.isNotEmpty() && !f.any { any ->
            any is Map<*, *> || any is Entity
        }
    }

    val subEntities: Set<EntityOrReference> by lazy {
        listOf(
            map,
            flatMap,
            flatten,
        )
            .flattenAny()
            .mapNotNull { entry ->
                when (entry) {
                    is Entity ->
                        EntityOrReferenceEntity(entry)

                    is Reference<*> ->
                        EntityOrReferenceReference(entry)

                    is Instance<*> ->
                        EntityOrReferenceEntity(entry.referenced)

                    else ->
                        null
                }
            }
            .toSet()
    }

    val subEntitiesRecursive: Set<EntityOrReference> by lazy {
        setOf(EntityOrReferenceEntity(entity)) + subEntitiesRecursive(subEntities)
    }

    val references: Set<Reference<*>> by lazy {
        subEntitiesRecursive
            .filterIsInstance<EntityOrReferenceReference>()
            .map(EntityOrReferenceReference::reference)
            .toSet()
    }

    val parsedReferences: Set<ParsedReference> by lazy {
        references.map(Reference<*>::parsedReference).toSet()
    }
}

private tailrec fun subEntitiesRecursive(sub: Set<EntityOrReference>): Set<EntityOrReference> {
    val entities: Set<EntityOrReference> = sub + sub.flatMap { entry ->
        when(entry) {
            is EntityOrReferenceEntity ->
                setOf(entry) + entry.entity.entityDescriptor.subEntitiesRecursive

            is EntityOrReferenceReference ->
                setOf(entry)
        }
    }
    return if (entities == sub) {
        entities
    } else {
        subEntitiesRecursive(entities)
    }
}
