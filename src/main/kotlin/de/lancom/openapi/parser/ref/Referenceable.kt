package de.lancom.openapi.parser.ref

import de.lancom.openapi.parser.entity.Entity

interface Referenceable : Entity {
    val __referenceName: String?

    fun getReferencePath(): String?
}

fun <R : Referenceable> R.reference(): Reference<R> {
    val reference = getReferencePath() ?: throw NotImplementedError("reference not set")
    return Reference(reference)
}
