package de.lancom.openapi.simple.util

import de.lancom.openapi.common.types.SchemaFormat
import de.lancom.openapi.common.types.SchemaType
import de.lancom.openapi.simple.entity.Schema

val EMPTY_SCHEMA = Schema()

val ARRAY_SCHEMA = Schema(
    type = SchemaType.Array,
)

val OBJECT_SCHEMA = Schema(
    type = SchemaType.Object,
)

val STRING_SCHEMA = Schema(
    type = SchemaType.String,
)

val NUMBER_SCHEMA = Schema(
    type = SchemaType.Number,
)

val INT_SCHEMA = Schema(
    type = SchemaType.Integer,
)

val UUID_SCHEMA = STRING_SCHEMA.copy(
    format = SchemaFormat.UUID,
)

val INT32_SCHEMA = INT_SCHEMA.copy(
    format = SchemaFormat.INT32,
)

val INT64_SCHEMA = INT_SCHEMA.copy(
    format = SchemaFormat.INT64,
)

val STRING_ARRAY_SCHEMA = ARRAY_SCHEMA.copy(
    items = STRING_SCHEMA,
)
