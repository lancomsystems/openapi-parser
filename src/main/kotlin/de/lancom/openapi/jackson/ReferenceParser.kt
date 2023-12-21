package de.lancom.openapi.jackson

import de.lancom.openapi.field.Field
import de.lancom.openapi.refs.ReferenceOrInstance
import de.lancom.openapi.refs.Referenceable

// TODO?
interface ReferenceParser<R : Referenceable> : Parser<R> {
    fun parseReferenceOrEntity(
        wrapper: Wrapper
    ): Field<ReferenceOrInstance<R>> {
        return wrapper.getReferenceOrEntity(::parseWrapper)
    }

    fun parseReferenceOrEntityOpt(
        wrapper: Wrapper
    ): Field<ReferenceOrInstance<R>?> {
        return wrapper.getNullOrElse {
            parseReferenceOrEntity(wrapper)
        }
    }
}
