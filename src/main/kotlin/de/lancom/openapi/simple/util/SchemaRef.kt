package de.lancom.openapi.simple.util

import de.lancom.openapi.simple.entity.SchemaReference

inline fun <reified T> schemaRef(): SchemaReference {
    return schemaRef(T::class.simpleName!!)
}

fun schemaRef(typeName: String): SchemaReference {
    return SchemaReference.fromName(typeName)
}
