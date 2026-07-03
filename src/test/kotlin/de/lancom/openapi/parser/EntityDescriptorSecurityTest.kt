package de.lancom.openapi.parser

import tools.jackson.module.kotlin.readValue
import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.ValidParsedReference
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.OpenApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EntityDescriptorSecurityTest {
    @Test
    fun `anonymous security alternatives are not parsed as scheme references`() {
        val given: OpenApi = yamlMapper.readValue(
            """
            openapi: 3.1.0
            info:
              title: Test
              version: 1.0.0
            security:
              - {}
              - apiKey: []
            paths:
              /profile:
                get:
                  security:
                    - {}
                    - bearerAuth: []
                  responses:
                    200:
                      description: OK
            components:
              securitySchemes:
                apiKey:
                  type: apiKey
                  in: header
                  name: X-API-Key
                bearerAuth:
                  type: http
                  scheme: bearer
                unused:
                  type: http
                  scheme: basic
            """.trimIndent()
        )

        val expected = setOf(
            ValidParsedReference(ComponentType.SecuritySchemes, "apiKey"),
            ValidParsedReference(ComponentType.SecuritySchemes, "bearerAuth"),
        )

        Assertions.assertEquals(expected, given.entityDescriptor.parsedReferences)
    }
}
