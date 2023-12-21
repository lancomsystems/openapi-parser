package de.lancom.openapi.entity

interface Entity {
    fun mergeEntity(other: Entity?): Entity

    val entityDescriptor: EntityDescriptor
}
