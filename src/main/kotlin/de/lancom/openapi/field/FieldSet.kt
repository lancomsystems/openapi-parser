package de.lancom.openapi.field

internal fun <T : Any?> setField(value: T): Field<T> {
    return FieldSet(value)
}

fun <T> List<Field<out T>>.getFields(): List<T> {
    return filterIsInstance<FieldSet<T>>().map(FieldSet<T>::value)
}

fun <K, V> Map<K, Field<out V>>.getFields(): Map<K, V> {
    return mapNotNull { (key, field) ->
        if (field is FieldSet<out V>) {
            key to field.value
        } else {
            null
        }
    }.toMap()
}

private data class FieldSet<T : Any?>(
    val value: T
) : Field<T> {
    @Suppress("UNCHECKED_CAST")
    override val optional: Field<T?> = this as Field<T?>

    override val isDefined: Boolean = true

    override val orNull: T
        get() = value

    override fun getOrError(): T {
        return value
    }

    override fun getOrElse(default: () -> T): T {
        return value
    }

    override fun orDefault(default: () -> T): Field<T> {
        return this
    }

    override fun orElse(fallback: () -> Field<T>): Field<T> {
        return this
    }

    override fun <E> map(mapper: (T) -> E): Field<E> {
        return Field(mapper(value))
    }

    override fun <E> flatMap(mapper: (T) -> Field<E>): Field<E> {
        return mapper(value)
    }

    override fun <E> mapField(mapper: (Field<T>) -> E): Field<E> {
        return Field(mapper(this))
    }
}
