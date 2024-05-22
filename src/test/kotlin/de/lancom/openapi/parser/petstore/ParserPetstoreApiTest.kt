package de.lancom.openapi.parser.petstore

import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.common.util.assertYamlEquals
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.OpenApi
import de.lancom.openapi.parser.entity.PathItem
import de.lancom.openapi.parser.entity.Paths
import de.lancom.openapi.parser.util.apiEndpoints
import de.lancom.openapi.parser.util.filterApiEndpoints
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class ParserPetstoreApiTest {
    val file = File("src/test/resources/examples/v30/petstore-expanded.yaml")

    @Test
    fun testPetstoreComponents() {
        val given: OpenApi = petstoreApi.setPaths(
            Paths()
                .addPathItem(
                    "/pets",
                    PathItem()
                        .addGet(findPets)
                )
        )
        val actual: String = given.toString()
        val expected: String = """
            openapi: 3.0.0
            info:
              version: 1.0.0
              title: Swagger Petstore
              description: A sample API that uses a petstore as an example to demonstrate features
                in the OpenAPI 3.0 specification
              termsOfService: http://swagger.io/terms/
              contact:
                name: Swagger API Team
                email: apiteam@swagger.io
                url: http://swagger.io
              license:
                name: Apache 2.0
                url: https://www.apache.org/licenses/LICENSE-2.0.html
            servers:
            - url: https://petstore.swagger.io/v2
            paths:
              /pets:
                get:
                  operationId: findPets
                  description: |
                    Returns all pets from the system that the user has access to
                    Nam sed condimentum est. Maecenas tempor sagittis sapien, nec rhoncus sem sagittis sit amet. Aenean at gravida augue, ac iaculis sem. Curabitur odio lorem, ornare eget elementum nec, cursus id lectus. Duis mi turpis, pulvinar ac eros ac, tincidunt varius justo. In hac habitasse platea dictumst. Integer at adipiscing ante, a sagittis ligula. Aenean pharetra tempor ante molestie imperdiet. Vivamus id aliquam diam. Cras quis velit non tortor eleifend sagittis. Praesent at enim pharetra urna volutpat venenatis eget eget mauris. In eleifend fermentum facilisis. Praesent enim enim, gravida ac sodales sed, placerat id erat. Suspendisse lacus dolor, consectetur non augue vel, vehicula interdum libero. Morbi euismod sagittis libero sed lacinia.
            
                    Sed tempus felis lobortis leo pulvinar rutrum. Nam mattis velit nisl, eu condimentum ligula luctus nec. Phasellus semper velit eget aliquet faucibus. In a mattis elit. Phasellus vel urna viverra, condimentum lorem id, rhoncus nibh. Ut pellentesque posuere elementum. Sed a varius odio. Morbi rhoncus ligula libero, vel eleifend nunc tristique vitae. Fusce et sem dui. Aenean nec scelerisque tortor. Fusce malesuada accumsan magna vel tempus. Quisque mollis felis eu dolor tristique, sit amet auctor felis gravida. Sed libero lorem, molestie sed nisl in, accumsan tempor nisi. Fusce sollicitudin massa ut lacinia mattis. Sed vel eleifend lorem. Pellentesque vitae felis pretium, pulvinar elit eu, euismod sapien.
                  parameters:
                  - in: query
                    name: tags
                    description: tags to filter by
                    required: false
                    style: form
                    schema:
                      type: array
                      items:
                        type: string
                  - in: query
                    name: limit
                    description: maximum number of results to return
                    required: false
                    schema:
                      type: integer
                      format: int32
                  responses:
                    default:
                      description: unexpected error
                      content:
                        application/json:
                          schema:
                            ${"$"}ref: '#/components/schemas/Error'
                    "200":
                      description: pet response
                      content:
                        application/json:
                          schema:
                            type: array
                            items:
                              ${"$"}ref: '#/components/schemas/Pet'
            components:
              schemas:
                Error:
                  type: object
                  properties:
                    code:
                      type: integer
                      format: int32
                    message:
                      type: string
                  required:
                  - code
                  - message
                NewPet:
                  type: object
                  properties:
                    name:
                      type: string
                    tag:
                      type: string
                  required:
                  - name
                Pet:
                  allOf:
                  - ${"$"}ref: '#/components/schemas/NewPet'
                  - type: object
                    properties:
                      id:
                        type: integer
                        format: int64
                    required:
                    - id

        """.trimIndent()
        assertEquals(expected, actual)
    }

    @Test
    fun testPetstoreYaml() {
        assertYamlEquals(
            expectedTree = yamlMapper.readTree(file),
            actualTree = yamlMapper.valueToTree(petstoreApi)
        )
    }

    @Test
    fun testApiEndpoints() {
        val expected = mapOf(
            "/pets" to mapOf(
                OperationMethod.Get to findPets,
                OperationMethod.Post to addPet,
            ),
            "/pets/{id}" to mapOf(
                OperationMethod.Delete to deletePet,
                OperationMethod.Get to findPetById,
            ),
        )
        val actual = petstoreApi.apiEndpoints()

        assertYamlEquals(expected, actual)
    }

    @Test
    fun testFilterApiEndpoints() {
        val expected = petstoreApi
            .setPaths(
                Paths()
                    .addPathItem(
                        "/pets" to PathItem()
                            .addGet(findPets)
                    )
            )
        val actual = petstoreApi.filterApiEndpoints(
            path = "/pets",
            operationType = OperationMethod.Get,
        )

        assertYamlEquals(expected, actual)
    }
}
