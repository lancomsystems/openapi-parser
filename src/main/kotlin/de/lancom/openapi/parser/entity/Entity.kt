package de.lancom.openapi.parser.entity

interface Entity {
    fun mergeEntity(other: Entity?): Entity

    val entityDescriptor: EntityDescriptor
}
