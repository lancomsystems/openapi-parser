package de.lancom.openapi.codegen.entity

import de.lancom.openapi.codegen.field.*
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.view.OpenApiEntity

val schema = OpenApiEntity(
    entityType = EntityType.Schema,
    fields = listOf(
        fieldString.name("title"),
        fieldInt.name("multipleOf"),
        fieldInt.name("maximum"),
        fieldBooleanRequiredDefaultFalse.name("exclusiveMaximum"),
        fieldInt.name("minimum"),
        fieldBooleanRequiredDefaultFalse.name("exclusiveMinimum"),
        fieldInt.name("maxLength"),
        fieldInt.default0().name("minLength"),
        fieldRegex.name("pattern"),
        fieldInt.name("maxItems"),
        fieldInt.default0().name("minItems"),
        fieldBooleanRequiredDefaultFalse.name("uniqueItems"),
        fieldInt.name("maxProperties"),
        fieldInt.default0().name("minProperties"),
        fieldStringSet.name("required"),
        fieldStringSet.name("enum"),
        fieldSchemaType.name("type"),
        fieldSchemaOrRef.name("not"),
        fieldSchemaOrRefList.name("allOf"),
        fieldSchemaOrRefList.name("oneOf"),
        fieldSchemaOrRefList.name("anyOf"),
        fieldSchemaOrRef.name("items"),
        fieldSchemaOrRef.map().name("properties"),
        fieldAdditionalProperties.default("AdditionalPropertiesBoolean").name("additionalProperties"),
        fieldString.name("description"),
        fieldStringFormat.name("format"),
        fieldDefaultJson.name("default"),
        fieldBooleanRequiredDefaultFalse.name("nullable"),
        fieldDiscriminator.name("discriminator"),
        fieldBooleanRequiredDefaultFalse.name("readOnly"),
        fieldBooleanRequiredDefaultFalse.name("writeOnly"),
        fieldExampleJson.name("example"),
        fieldExternalDocumentation.name("externalDocs"),
        fieldBooleanRequiredDefaultFalse.name("deprecated"),
        fieldXML.name("xml"),
    )
)
