package de.lancom.openapi.simple.petstore

import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.common.util.assertYamlEquals
import de.lancom.openapi.common.util.simpleOpenApiParser
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.simple.entity.OpenApi
import de.lancom.openapi.simple.entity.PathItem
import de.lancom.openapi.simple.entity.Paths
import de.lancom.openapi.simple.util.OperationInfo
import de.lancom.openapi.simple.util.filterApiEndpoints
import de.lancom.openapi.simple.util.filterApiEndpointsStartsWith
import de.lancom.openapi.simple.util.operations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SimplePetstoreApiTest {
    val file = File("src/test/resources/examples/v30/petstore-expanded.yaml")

    @Test
    fun testPetstoreYaml() {
        val openApi: OpenApi = simpleOpenApiParser(file)
        val fileYaml = yamlMapper.writeValueAsString(openApi.components)
        val simpleYaml = yamlMapper.writeValueAsString(petstoreApi.components)
        assertYamlEquals(fileYaml, simpleYaml)
        assertEquals(petstoreApi, openApi)
    }

    @Test
    fun testApiEndpoints() {
        val expected = listOf(
            OperationInfo(
                path = petsPath,
                pathItem = petstoreApi.paths.pathItems[petsPath]!!,
                operation = addPet,
                operationMethod = OperationMethod.Post,
            ),
            OperationInfo(
                path = petsPath,
                pathItem = petstoreApi.paths.pathItems[petsPath]!!,
                operation = findPets,
                operationMethod = OperationMethod.Get,
            ),
            OperationInfo(
                path = petByIdPath,
                pathItem = petstoreApi.paths.pathItems[petByIdPath]!!,
                operation = deletePet,
                operationMethod = OperationMethod.Delete,
            ),
            OperationInfo(
                path = petByIdPath,
                pathItem = petstoreApi.paths.pathItems[petByIdPath]!!,
                operation = findPetById,
                operationMethod = OperationMethod.Get,
            ),
        )
        val actual = petstoreApi.operations

        assertYamlEquals(expected, actual)
    }

    @Test
    fun testFilterApiEndpoints() {
        val expected = petstoreApi
            .addPaths(
                Paths()
                    .addPathItem(
                        petByIdPath,
                        PathItem()
                            .addGet(findPetById)
                    )
            )
        val actual = petstoreApi.filterApiEndpoints(
            path = petByIdPath,
            operationMethod = OperationMethod.Get,
        )

        assertYamlEquals(expected, actual)
    }

    @Test
    fun testFilterApiEndpointsStartsWith() {
        val expected = petstoreApi
            .addPaths(
                Paths()
                    .addPathItem(
                        petByIdPath,
                        PathItem()
                            .addDelete(deletePet)
                            .addGet(findPetById)
                    )
            )
        val actual = petstoreApi.filterApiEndpointsStartsWith(
            path = petByIdPath,
        )

        assertYamlEquals(expected, actual)
    }
}
