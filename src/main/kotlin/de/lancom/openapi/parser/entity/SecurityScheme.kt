package de.lancom.openapi.parser.entity

import de.lancom.openapi.parser.jackson.ReferenceParser
import de.lancom.openapi.parser.jackson.Wrapper
import de.lancom.openapi.parser.ref.Referenceable

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
