package de.lancom.openapi.field

internal fun <T : Any?> unsetField(): Field<T> {
    @Suppress("UNCHECKED_CAST")
    return FieldUnset as Field<T>
}

private object FieldUnset : Field<Any?> {
    override val optional: Field<Any?> = this

    override val isDefined: Boolean = false

    override val orNull: Any? = null

    override fun getOrError(): Any? {
        TODO()
    }

    override fun getOrElse(default: () -> Any?): Any? {
        return default()
    }

    override fun orDefault(default: () -> Any?): Field<Any?> {
        return Field(default())
    }

    override fun orElse(fallback: () -> Field<Any?>): Field<Any?> {
        return fallback()
    }

    override fun <E> map(mapper: (Any?) -> E): Field<E> {
        return Field.unset()
    }

    override fun <E> flatMap(mapper: (Any?) -> Field<E>): Field<E> {
        return Field.unset()
    }

    override fun <E> mapField(mapper: (Field<Any?>) -> E): Field<E> {
        return Field.unset()
    }
}
