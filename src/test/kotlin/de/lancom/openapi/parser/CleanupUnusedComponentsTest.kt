package de.lancom.openapi.parser

import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.util.cleanupUnusedComponents
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CleanupUnusedComponentsTest {
    @Test
    fun test() {
        val given: OpenApi = yamlMapper.readValue(
            """
            openapi: 3.0.0
            info:
              title: Test
              version: 1.0.0
            paths:
              /test:
                get:
                  parameters:
                  - name: test
                    in: query
                    schema:
                      ${"$"}ref: '#/components/schemas/ReferencedDirectly'
                  responses:
                    200:
                      ${"$"}ref: '#/components/responses/ReferencedDirectly'
            components:
              responses:
                Unreferenced:
                  content:
                    application/json:
                      schema:
                        ${"$"}ref: '#/components/schemas/Unreferenced'
                ReferencedDirectly:
                  content:
                    application/json:
                      schema:
                        ${"$"}ref: '#/components/schemas/ReferencedIndirectly'
              schemas:
                ReferencedDirectly:
                  type: string
                ReferencedIndirectly:
                  type: string
                Unreferenced:
                  type: string
            """.trimIndent()
        )
        val actual: OpenApi = given.cleanupUnusedComponents()
        val expected: OpenApi = yamlMapper.readValue(
            """
            openapi: 3.0.0
            info:
              title: Test
              version: 1.0.0
            paths:
              /test:
                get:
                  parameters:
                  - name: test
                    in: query
                    schema:
                      ${"$"}ref: '#/components/schemas/ReferencedDirectly'
                  responses:
                    200:
                      ${"$"}ref: '#/components/responses/ReferencedDirectly'
            components:
              responses:
                ReferencedDirectly:
                  content:
                    application/json:
                      schema:
                        ${"$"}ref: '#/components/schemas/ReferencedIndirectly'
              schemas:
                ReferencedDirectly:
                  type: string
                ReferencedIndirectly:
                  type: string
            """.trimIndent()
        )
        Assertions.assertEquals(expected, actual)
    }
}
