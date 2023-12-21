package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.node.*
import de.lancom.openapi.jackson.Parser

interface JsonEntityFactory<E : Entity> : Parser<E> {
    operator fun invoke(number: Number): E {
        return when (number) {
            is Int ->
                this(number)

            is Long ->
                this(number)

            is Double ->
                this(number)

            else ->
                throw NotImplementedError()
        }

    }

    operator fun invoke(int: Int): E {
        return parseJsonNode(IntNode(int))
    }

    operator fun invoke(long: Long): E {
        return parseJsonNode(LongNode(long))
    }

    operator fun invoke(double: Double): E {
        return parseJsonNode(DoubleNode(double))
    }

    operator fun invoke(boolean: Boolean): E {
        return parseJsonNode(if (boolean) BooleanNode.TRUE else BooleanNode.FALSE)
    }

    operator fun invoke(string: String): E {
        return parseJsonNode(TextNode(string))
    }
}
