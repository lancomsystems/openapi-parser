package de.lancom.openapi.parser.util

import de.lancom.openapi.parser.entity.Schema
import de.lancom.openapi.parser.ref.Reference

val ARRAY_SCHEMA: Schema = Schema()
    .addTypeArray()

val OBJECT_SCHEMA: Schema = Schema()
    .addTypeObject()

val STRING_SCHEMA = Schema()
    .addTypeString()

val NUMBER_SCHEMA = Schema()
    .addTypeNumber()

val INT_SCHEMA = Schema()
    .addTypeInteger()

val UUID_SCHEMA = STRING_SCHEMA
    .addFormatUuid()

val INT32_SCHEMA = INT_SCHEMA
    .addFormatInt32()

val INT64_SCHEMA = INT_SCHEMA
    .addFormatInt64()

val STRING_ARRAY_SCHEMA = ARRAY_SCHEMA
    .addItems(STRING_SCHEMA)

inline fun <reified T> schemaRef(): Reference<Schema> {
    return schemaRef(T::class.simpleName!!)
}

fun schemaRef(typeName: String): Reference<Schema> {
    return Reference("#/components/schemas/$typeName")
}
