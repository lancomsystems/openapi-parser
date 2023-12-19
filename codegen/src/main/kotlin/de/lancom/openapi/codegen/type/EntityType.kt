package de.lancom.openapi.codegen.type

data class EntityType(
    val name: String,
    val referenceable: Boolean = false,
    val extensions: Boolean = true,
    override val required: Boolean = false,
) : Type() {
    override val type: String = name

    override fun required(): EntityType {
        return copy(required = true)
    }

    override val imports: Set<String> = if (referenceable) {
        setOf(
            "de.lancom.openapi.refs.Instance",
        )
    } else {
        emptySet()
    }

    companion object {
        val APIKeySecurityScheme = EntityType("APIKeySecurityScheme", referenceable = true)
        val AdditionalProperties = EntityType("AdditionalProperties")
        val Callback = EntityType("Callback", referenceable = true)
        val Components = EntityType("Components")
        val Contact = EntityType("Contact")
        val DefaultJson = EntityType("DefaultJson")
        val Discriminator = EntityType("Discriminator")
        val Encoding = EntityType("Encoding")
        val Example = EntityType("Example", referenceable = true)
        val ExampleJson = EntityType("ExampleJson")
        val ExternalDocumentation = EntityType("ExternalDocumentation")
        val HTTPSecurityScheme = EntityType("HTTPSecurityScheme", referenceable = true)
        val Header = EntityType("Header", referenceable = true)
        val Info = EntityType("Info")
        val License = EntityType("License")
        val Link = EntityType("Link", referenceable = true)
        val MediaType = EntityType("MediaType")
        val OAuth2SecurityScheme = EntityType("OAuth2SecurityScheme", referenceable = true)
        val OpenApi = EntityType("OpenApi")
        val OpenIdConnectSecurityScheme = EntityType("OpenIdConnectSecurityScheme", referenceable = true)
        val Operation = EntityType("Operation")
        val Parameter = EntityType("Parameter", referenceable = true)
        val PathItem = EntityType("PathItem")
        val Paths = EntityType("Paths")
        val RequestBody = EntityType("RequestBody", referenceable = true)
        val RequestBodyJson = EntityType("RequestBodyJson")
        val Response = EntityType("Response", referenceable = true)
        val Responses = EntityType("Responses")
        val Schema = EntityType("Schema", referenceable = true)
        val SecurityRequirement = EntityType("SecurityRequirement")
        val SecurityScheme = EntityType("SecurityScheme", referenceable = true)
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
