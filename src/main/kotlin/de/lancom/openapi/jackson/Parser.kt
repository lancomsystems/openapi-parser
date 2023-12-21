package de.lancom.openapi.jackson

import com.fasterxml.jackson.databind.JsonNode
import de.lancom.openapi.entity.Entity
import de.lancom.openapi.field.Field

interface Parser<E : Entity> {
    fun parseJsonString(json: String): E {
        return parseWrapper(Wrapper.parseJsonString(json))
    }

    fun parseJsonNode(jsonNode: JsonNode): E {
        return parseWrapper(Wrapper(jsonNode))
    }

    fun parseEntity(wrapper: Wrapper): Field<E> {
        return wrapper.getSingle {
            Field(parseWrapper(wrapper))
        }
    }

    fun parseEntityOpt(wrapper: Wrapper): Field<E?> {
        return wrapper.getNullOrElse {
            parseEntity(wrapper)
        }
    }

    fun parseWrapper(wrapper: Wrapper): E
}
