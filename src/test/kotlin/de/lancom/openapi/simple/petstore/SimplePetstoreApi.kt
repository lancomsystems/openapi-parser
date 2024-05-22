package de.lancom.openapi.simple.petstore

import de.lancom.openapi.common.types.In
import de.lancom.openapi.common.types.ParameterStyle
import de.lancom.openapi.common.types.Path
import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.simple.entity.*
import de.lancom.openapi.simple.util.*

interface Pet

interface NewPet

interface Error

val parameterTagFilter = Parameter(
    `in` = In.Query,
    name = "tags",
    description = "tags to filter by",
    style = ParameterStyle.Form,
    schema = STRING_ARRAY_SCHEMA,
)

val parameterPathPetId = Parameter(
    `in` = In.Path,
    name = "id",
    required = true,
    schema = INT64_SCHEMA,
)

val parameterLimit = Parameter(
    `in` = In.Query,
    name = "limit",
    description = "maximum number of results to return",
    schema = INT32_SCHEMA,
)

val SCHEMA_REF_PET = schemaRef<Pet>()

val SCHEMA_REF_NEW_PET = schemaRef<NewPet>()

val SCHEMA_REF_ERROR = schemaRef<Error>()

val SCHEMA_PET_ARRAY = ARRAY_SCHEMA.copy(
    items = SCHEMA_REF_PET
)

val SCHEMA_NEW_PET = OBJECT_SCHEMA
    .addComponentName("NewPet")
    .addRequiredProperty("name", STRING_SCHEMA)
    .addProperty("tag", STRING_SCHEMA)

val SCHEMA_PET_ID = OBJECT_SCHEMA
    .addRequiredProperty("id", INT64_SCHEMA)

val SCHEMA_PET = Schema()
    .addComponentName("Pet")
    .addAllOf(
        SCHEMA_REF_NEW_PET,
        SCHEMA_PET_ID,
    )

val SCHEMA_ERROR = OBJECT_SCHEMA
    .addComponentName("Error")
    .addRequiredProperty("code", INT32_SCHEMA)
    .addRequiredProperty("message", STRING_SCHEMA)

val errorResponse = Response()
    .addDescription("unexpected error")
    .addJsonContent(SCHEMA_REF_ERROR)

val DEFAULT_RESPONSES: Map<ResponseStatusCode, Response> = mapOf(
    ResponseStatusCode.DEFAULT to errorResponse
)

val RESPONSE_PET = Response()
    .addDescription("pet response")
    .addJsonContent(SCHEMA_REF_PET)

val RESPONSE_PET_ARRAY = Response()
    .addDescription("pet response")
    .addJsonContent(SCHEMA_PET_ARRAY)

val findPets = Operation()
    .addOperationId("findPets")
    .addDescription(
        """
        Returns all pets from the system that the user has access to
        Nam sed condimentum est. Maecenas tempor sagittis sapien, nec rhoncus sem sagittis sit amet. Aenean at gravida augue, ac iaculis sem. Curabitur odio lorem, ornare eget elementum nec, cursus id lectus. Duis mi turpis, pulvinar ac eros ac, tincidunt varius justo. In hac habitasse platea dictumst. Integer at adipiscing ante, a sagittis ligula. Aenean pharetra tempor ante molestie imperdiet. Vivamus id aliquam diam. Cras quis velit non tortor eleifend sagittis. Praesent at enim pharetra urna volutpat venenatis eget eget mauris. In eleifend fermentum facilisis. Praesent enim enim, gravida ac sodales sed, placerat id erat. Suspendisse lacus dolor, consectetur non augue vel, vehicula interdum libero. Morbi euismod sagittis libero sed lacinia.

        Sed tempus felis lobortis leo pulvinar rutrum. Nam mattis velit nisl, eu condimentum ligula luctus nec. Phasellus semper velit eget aliquet faucibus. In a mattis elit. Phasellus vel urna viverra, condimentum lorem id, rhoncus nibh. Ut pellentesque posuere elementum. Sed a varius odio. Morbi rhoncus ligula libero, vel eleifend nunc tristique vitae. Fusce et sem dui. Aenean nec scelerisque tortor. Fusce malesuada accumsan magna vel tempus. Quisque mollis felis eu dolor tristique, sit amet auctor felis gravida. Sed libero lorem, molestie sed nisl in, accumsan tempor nisi. Fusce sollicitudin massa ut lacinia mattis. Sed vel eleifend lorem. Pellentesque vitae felis pretium, pulvinar elit eu, euismod sapien.

        """.trimIndent()
    )
    .addParameters(parameterTagFilter)
    .addParameters(parameterLimit)
    .addResponses(DEFAULT_RESPONSES)
    .addResponse(
        ResponseStatusCode.HTTP_200_OK to RESPONSE_PET_ARRAY
    )

val findPetById = Operation()
    .addOperationId("find pet by id")
    .addDescription("Returns a user based on a single ID, if the user does not have access to the pet")
    .addParameters(parameterPathPetId.addDescription("ID of pet to fetch"))
    .addResponses(DEFAULT_RESPONSES)
    .addResponses(
        ResponseStatusCode.HTTP_200_OK to RESPONSE_PET
    )

val addPet = Operation()
    .addOperationId("addPet")
    .addDescription("Creates a new pet in the store. Duplicates are allowed")
    .addRequestBody(
        RequestBody()
            .addDescription("Pet to add to the store")
            .addRequired()
            .addJsonContent(SCHEMA_REF_NEW_PET)
    )
    .addResponses(DEFAULT_RESPONSES)
    .addResponses(
        ResponseStatusCode.HTTP_200_OK to RESPONSE_PET
    )

val deletePet = Operation()
    .addOperationId("deletePet")
    .addDescription("deletes a single pet based on the ID supplied")
    .addParameters(parameterPathPetId.addDescription("ID of pet to delete"))
    .addResponses(DEFAULT_RESPONSES)
    .addResponses(
        ResponseStatusCode.HTTP_204_NO_CONTENT to Response().addDescription("pet deleted")
    )

val petstoreComponents = Components()
    .addSchemas(
        SCHEMA_PET,
        SCHEMA_NEW_PET,
        SCHEMA_ERROR,
    )

val petsPath = Path("/pets")
val petByIdPath = Path("/pets/{id}")

val petstoreApi = OpenApi()
    .addPaths(
        Paths()
            .addPathItem(
                petsPath,
                PathItem()
                    .addGet(findPets)
                    .addPost(addPet)
            )
            .addPathItem(
                petByIdPath,
                PathItem()
                    .addDelete(deletePet)
                    .addGet(findPetById)
            )
    )
    .addComponents(petstoreComponents)
    .addOpenapi("3.0.0")
    .addServers(
        listOf(
            Server()
                .addUrl("https://petstore.swagger.io/v2")
        )
    )
    .addInfo(
        Info()
            .addContact(
                Contact()
                    .addEmail("apiteam@swagger.io")
                    .addName("Swagger API Team")
                    .addUrl("http://swagger.io")
            )
            .addDescription("A sample API that uses a petstore as an example to demonstrate features in the OpenAPI 3.0 specification")
            .addLicense(
                License()
                    .addName("Apache 2.0")
                    .addUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
            )
            .addTermsOfService("http://swagger.io/terms/")
            .addTitle("Swagger Petstore")
            .addVersion("1.0.0")
    )
