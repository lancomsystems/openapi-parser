package de.lancom.openapi.field

sealed interface Field<T : Any?> {
    val optional: Field<T?>

    val isDefined: Boolean

    val orNull: T?

    fun getOrError(): T

    fun getOrElse(default: () -> T): T

    fun orDefault(default: () -> T): Field<T>

    fun orElse(fallback: () -> Field<T>): Field<T>

    fun <E> map(mapper: (T) -> E): Field<E>

    fun <E> flatMap(mapper: (T) -> Field<E>): Field<E>

    fun <E> mapField(mapper: (Field<T>) -> E): Field<E>

    fun takeUnlessField(predicate: (T) -> Boolean): Field<T> {
        return flatMap { value ->
            if (predicate(value)) {
                unset()
            } else {
                this
            }
        }
    }

    companion object {
        fun <T : Any?> unset(): Field<T> {
            return unsetField()
        }

        operator fun <T : Any?> invoke(value: T): Field<T> {
            return setField(value)
        }

        fun <T> unsetIfNull(value: T?): Field<T> {
            return if (value == null) {
                unset()
            } else {
                Field(value)
            }
        }

        fun <K, V> takeUnlessEmpty(value: Map<K, V>?): Field<Map<K, V>> {
            return if (value.isNullOrEmpty()) {
                unset()
            } else {
                Field(value)
            }
        }

        fun <V> takeUnlessEmpty(value: List<V>?): Field<List<V>> {
            return if (value.isNullOrEmpty()) {
                unset()
            } else {
                Field(value)
            }
        }
    }
}
