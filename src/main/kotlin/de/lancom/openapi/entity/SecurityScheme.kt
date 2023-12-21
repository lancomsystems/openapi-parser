package de.lancom.openapi.entity

import de.lancom.openapi.jackson.ReferenceParser
import de.lancom.openapi.jackson.Wrapper
import de.lancom.openapi.refs.Referenceable

sealed interface SecurityScheme : Referenceable {
    val type: SecuritySchemeType

    companion object : ReferenceParser<SecurityScheme> {
        override fun parseWrapper(wrapper: Wrapper): SecurityScheme {
            val securitySchemeType = wrapper["type"]
                .getEnum(SecuritySchemeType::valueOf)
                .getOrError()

            return securitySchemeType.parser.parseWrapper(wrapper)
        }
    }
}
