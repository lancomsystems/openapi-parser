package de.lancom.openapi.parser.jackson

import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

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
