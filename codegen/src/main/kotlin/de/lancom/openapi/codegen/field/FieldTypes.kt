package de.lancom.openapi.codegen.field

import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.type.EnumType
import de.lancom.openapi.codegen.type.SimpleType

val fieldAdditionalProperties = EntityType.AdditionalProperties()
val fieldBoolean = SimpleType.boolean()
val fieldBooleanRequiredDefaultFalse = SimpleType.boolean().defaultFalse()
val fieldCallbackOrRef = EntityType.Callback().referenceOrInstance()
val fieldComponents = EntityType.Components()
val fieldContact = EntityType.Contact()
val fieldDefaultJson = EntityType.DefaultJson()
val fieldDiscriminator = EntityType.Discriminator()
val fieldDouble = SimpleType.double()
val fieldEncoding = EntityType.Encoding()
val fieldExampleJson = EntityType.ExampleJson()
val fieldExampleOrRef = EntityType.Example().referenceOrInstance()
val fieldExtension = EntityType.Extension()
val fieldExternalDocumentation = EntityType.ExternalDocumentation()
val fieldHeader = EntityType.Header()
val fieldHeaderOrRef = EntityType.Header().referenceOrInstance()
val fieldHeaderStyle = EnumType.HeaderStyle()
val fieldIn = EnumType.In()
val fieldInApiKey = EnumType.InApiKey()
val fieldInfo = EntityType.Info()
val fieldInt = SimpleType.int()
val fieldLicense = EntityType.License()
val fieldLinkOrRef = EntityType.Link().referenceOrInstance()
val fieldMediaType = EntityType.MediaType()
val fieldOperation = EntityType.Operation()
val fieldParameterOrRef = EntityType.Parameter().referenceOrInstance()
val fieldPathItem = EntityType.PathItem()
val fieldPaths = EntityType.Paths()
val fieldRawJson = SimpleType.jsonNode().override().required().flat()
val fieldRegex = SimpleType.string().regex()
val fieldRequestBodyJson = EntityType.RequestBodyJson()
val fieldRequestBodyOrRef = EntityType.RequestBody().referenceOrInstance()
val fieldResponseOrRef = EntityType.Response().referenceOrInstance()
val fieldResponses = EntityType.Responses()
val fieldSchema = EntityType.Schema()
val fieldSchemaOrRef = EntityType.Schema().referenceOrInstance()
val fieldSchemaOrRefList = EntityType.Schema().referenceOrInstance().list()
val fieldSchemaRef = EntityType.Schema().reference()
val fieldSchemaType = EnumType.SchemaType()
val fieldSecurityRequirementList = EntityType.SecurityRequirement().list()
val fieldSecuritySchemeOrRef = EntityType.SecurityScheme().referenceOrInstance()
val fieldSecuritySchemeType = SimpleType.securitySchemeType()
val fieldServer = EntityType.Server()
val fieldServerList = EntityType.Server().list()
val fieldServerVariable = EntityType.ServerVariable()
val fieldString = SimpleType.string()
val fieldStringFormat = SimpleType.string().options("uuid", "int32", "int64")
val fieldStringList = SimpleType.string().list()
val fieldStringSet = SimpleType.string().set()
val fieldStyle = EnumType.Style()
val fieldTagList = EntityType.Tag().list()
val fieldXML = EntityType.XML()
