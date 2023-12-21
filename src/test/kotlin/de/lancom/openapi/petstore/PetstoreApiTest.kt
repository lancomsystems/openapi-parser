package de.lancom.openapi.petstore

import de.lancom.openapi.assertYamlEquals
import de.lancom.openapi.entity.OperationType
import de.lancom.openapi.entity.PathItem
import de.lancom.openapi.entity.Paths
import de.lancom.openapi.tools.apiEndpoints
import de.lancom.openapi.tools.filterApiEndpoints
import de.lancom.openapi.tools.yamlMapper
import org.junit.jupiter.api.Test
import java.io.File

class PetstoreApiTest {
    val file = File("src/test/resources/examples/v30/petstore-expanded.yaml")

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
                OperationType.get to findPets,
                OperationType.post to addPet,
            ),
            "/pets/{id}" to mapOf(
                OperationType.delete to deletePet,
                OperationType.get to findPetById,
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
            operationType = OperationType.get,
        )

        assertYamlEquals(expected, actual)
    }
}
