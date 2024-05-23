package de.lancom.openapi.codegen.type

data class EntityType(
    val name: String,
    val componentName: String? = null,
    val extensions: Boolean = true,
    override val required: Boolean = false,
) : Type() {
    override val type: String = name
    val referenceable: Boolean = componentName != null

    override fun required(): EntityType {
        return copy(required = true)
    }

    override val imports: Set<String> = if (referenceable) {
        setOf(
            "de.lancom.openapi.parser.ref.Instance",
        )
    } else {
        emptySet()
    }

    companion object {
        val APIKeySecurityScheme = EntityType("APIKeySecurityScheme", componentName = "securitySchemes")
        val AdditionalProperties = EntityType("AdditionalProperties")
        val Callback = EntityType("Callback", componentName = "callbacks")
        val Components = EntityType("Components")
        val Contact = EntityType("Contact")
        val DefaultJson = EntityType("DefaultJson")
        val Discriminator = EntityType("Discriminator")
        val Encoding = EntityType("Encoding")
        val Example = EntityType("Example", componentName = "examples")
        val ExampleJson = EntityType("ExampleJson")
        val ExternalDocumentation = EntityType("ExternalDocumentation")
        val HTTPSecurityScheme = EntityType("HTTPSecurityScheme", componentName = "securitySchemes")
        val Header = EntityType("Header", componentName = "headers")
        val Info = EntityType("Info")
        val License = EntityType("License")
        val Link = EntityType("Link", componentName = "links")
        val MediaType = EntityType("MediaType")
        val OAuth2SecurityScheme = EntityType("OAuth2SecurityScheme", componentName = "securitySchemes")
        val OpenApi = EntityType("OpenApi")
        val OpenIdConnectSecurityScheme = EntityType("OpenIdConnectSecurityScheme", componentName = "securitySchemes")
        val Operation = EntityType("Operation")
        val Parameter = EntityType("Parameter", componentName = "parameters")
        val PathItem = EntityType("PathItem")
        val Paths = EntityType("Paths")
        val RequestBody = EntityType("RequestBody", componentName = "requestBodies")
        val RequestBodyJson = EntityType("RequestBodyJson")
        val Response = EntityType("Response", componentName = "responses")
        val Responses = EntityType("Responses")
        val Schema = EntityType("Schema", componentName = "schemas")
        val SecurityRequirement = EntityType("SecurityRequirement")
        val SecurityScheme = EntityType("SecurityScheme", componentName = "securitySchemes")
        val Server = EntityType("Server")
        val ServerVariable = EntityType("ServerVariable")
        val Tag = EntityType("Tag")
        val XML = EntityType("XML")

        val Extension = EntityType("Extension")
        val RawExtension = EntityType("RawExtension")
        val TagGroupsExtension = EntityType("TagGroupsExtension")
        val TagGroupsExtensionEntry = EntityType("TagGroupsExtensionEntry")
    }
}
