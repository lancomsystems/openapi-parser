/*****************************************************************************
**   C A U T I O N                                                          **
**   This file is auto-generated!                                           **
**   If you want to make changes, please see the README.md file.            **
**   Please do not edit this file directly!                                 **
*****************************************************************************/
package de.lancom.openapi.entity

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.field.Field
import de.lancom.openapi.jackson.EntityDeserializer
import de.lancom.openapi.jackson.EntitySerializer
import de.lancom.openapi.jackson.ReferenceParser
import de.lancom.openapi.jackson.Wrapper
import de.lancom.openapi.tools.toYamlString

// hint:9A1BF04C
@Suppress("PropertyName")
@JsonSerialize(using = OpenIdConnectSecurityScheme.Companion.Serializer::class)
@JsonDeserialize(using = OpenIdConnectSecurityScheme.Companion.Deserializer::class)
data class OpenIdConnectSecurityScheme(
    val _openIdConnectUrl: Field<String?> = Field.unset(),
    val _description: Field<String?> = Field.unset(),
    val _extensions: Field<Map<String, Extension?>> = Field.unset(),
) : SecurityScheme {

    // hint:C89A3E1F
    override val type: SecuritySchemeType = SecuritySchemeType.openIdConnect

    ///////////////////////
    //
    // openIdConnectUrl
    //
    ///////////////////////

    // hint:3A7F9B2E
    val openIdConnectUrl: String?
        get() = _openIdConnectUrl.orNull

    // hint:F0C48D71
    fun setOpenIdConnectUrlField(openIdConnectUrl: Field<String?>): OpenIdConnectSecurityScheme {
        return copy(_openIdConnectUrl = openIdConnectUrl)
    }

    // hint:8E56A4D9
    fun updateOpenIdConnectUrlField(updater: (Field<String?>) -> Field<String?>): OpenIdConnectSecurityScheme {
        return setOpenIdConnectUrlField(updater(_openIdConnectUrl))
    }

    // hint:B1D730FC
    fun updateOpenIdConnectUrl(updater: (String?) -> String?): OpenIdConnectSecurityScheme {
        return updateOpenIdConnectUrlField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeOpenIdConnectUrlField(openIdConnectUrlFieldToMerge: Field<String?>): OpenIdConnectSecurityScheme {
        return mergeOpenIdConnectUrl(openIdConnectUrlFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeOpenIdConnectUrl(openIdConnectUrlToMerge: String?): OpenIdConnectSecurityScheme {
        return if (openIdConnectUrlToMerge == null) {
            this
        } else {
            val oldOpenIdConnectUrl = _openIdConnectUrl.orNull
            if (oldOpenIdConnectUrl == null) {
                setOpenIdConnectUrlField(Field(openIdConnectUrlToMerge))
            } else {
                // hint:2F684DAC
                setOpenIdConnectUrl(openIdConnectUrlToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setOpenIdConnectUrl(openIdConnectUrl: String?): OpenIdConnectSecurityScheme {
        return setOpenIdConnectUrlField(Field(openIdConnectUrl))
    }

    // hint:D465F782
    fun unsetOpenIdConnectUrl(): OpenIdConnectSecurityScheme {
        return setOpenIdConnectUrlField(Field.unset())
    }

    // hint:47C9A0F6
    fun addOpenIdConnectUrl(openIdConnectUrl: String): OpenIdConnectSecurityScheme {
        if (this.openIdConnectUrl != null) {
            throw IllegalStateException("Field openIdConnectUrl of Entity OpenIdConnectSecurityScheme is already set to '${this.openIdConnectUrl}', refused to add new value '$openIdConnectUrl'")
        }
        return setOpenIdConnectUrl(openIdConnectUrl)
    }

    ///////////////////////
    //
    // description
    //
    ///////////////////////

    // hint:3A7F9B2E
    val description: String?
        get() = _description.orNull

    // hint:F0C48D71
    fun setDescriptionField(description: Field<String?>): OpenIdConnectSecurityScheme {
        return copy(_description = description)
    }

    // hint:8E56A4D9
    fun updateDescriptionField(updater: (Field<String?>) -> Field<String?>): OpenIdConnectSecurityScheme {
        return setDescriptionField(updater(_description))
    }

    // hint:B1D730FC
    fun updateDescription(updater: (String?) -> String?): OpenIdConnectSecurityScheme {
        return updateDescriptionField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDescriptionField(descriptionFieldToMerge: Field<String?>): OpenIdConnectSecurityScheme {
        return mergeDescription(descriptionFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDescription(descriptionToMerge: String?): OpenIdConnectSecurityScheme {
        return if (descriptionToMerge == null) {
            this
        } else {
            val oldDescription = _description.orNull
            if (oldDescription == null) {
                setDescriptionField(Field(descriptionToMerge))
            } else {
                // hint:2F684DAC
                setDescription(descriptionToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setDescription(description: String?): OpenIdConnectSecurityScheme {
        return setDescriptionField(Field(description))
    }

    // hint:D465F782
    fun unsetDescription(): OpenIdConnectSecurityScheme {
        return setDescriptionField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDescription(description: String): OpenIdConnectSecurityScheme {
        if (this.description != null) {
            throw IllegalStateException("Field description of Entity OpenIdConnectSecurityScheme is already set to '${this.description}', refused to add new value '$description'")
        }
        return setDescription(description)
    }

    ///////////////////////
    //
    // extensions
    //
    ///////////////////////

    // hint:3A7F9B2E
    val extensions: Map<String, Extension?>
        get() = _extensions.orNull ?: emptyMap()

    // hint:F0C48D71
    fun setExtensionsField(extensions: Field<Map<String, Extension?>>): OpenIdConnectSecurityScheme {
        return copy(_extensions = extensions)
    }

    // hint:8E56A4D9
    fun updateExtensionsField(updater: (Field<Map<String, Extension?>>) -> Field<Map<String, Extension?>>): OpenIdConnectSecurityScheme {
        return setExtensionsField(updater(_extensions))
    }

    // hint:B1D730FC
    fun updateExtensions(updater: (Map<String, Extension?>) -> Map<String, Extension?>): OpenIdConnectSecurityScheme {
        return updateExtensionsField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeExtensionsField(extensionsFieldToMerge: Field<Map<String, Extension?>>): OpenIdConnectSecurityScheme {
        return mergeExtensions(extensionsFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeExtensions(extensionsToMerge: Map<String, Extension?>?): OpenIdConnectSecurityScheme {
        return if (extensionsToMerge == null) {
            this
        } else {
            val oldExtensions = _extensions.orNull
            if (oldExtensions == null) {
                setExtensionsField(Field(extensionsToMerge))
            } else {
                // hint:70A3D8B6
                setExtensions(de.lancom.openapi.tools.mergeMap(oldExtensions, extensionsToMerge))
            }
        }
    }

    // hint:87B3E19C
    fun setExtensions(extensions: Map<String, Extension?>): OpenIdConnectSecurityScheme {
        return setExtensionsField(Field(extensions))
    }

    // hint:D465F782
    fun unsetExtensions(): OpenIdConnectSecurityScheme {
        return setExtensionsField(Field.unset())
    }

    // hint:5C81D396
    fun addExtensions(extensions: Map<String, Extension?>): OpenIdConnectSecurityScheme {
        return mergeExtensions(extensions)
    }

    // hint:1A6B37F8
    fun addExtensions(vararg extensions: Pair<String, Extension?>): OpenIdConnectSecurityScheme {
        return addExtensions(extensions.toMap())
    }

    // hint:9D0E4CA5
    fun addExtension(key: String, value: Extension?): OpenIdConnectSecurityScheme {
        return addExtensions(key to value)
    }

    // hint:B8F25E73
    fun addExtension(pair: Pair<String, Extension?>): OpenIdConnectSecurityScheme {
        return addExtensions(mapOf(pair))
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = null,
            map = mapOf(
                "type" to Field(type),
                "openIdConnectUrl" to _openIdConnectUrl,
                "description" to _description,
            ),
            flatMap = listOf(
                _extensions,
            ),
            flatten = listOf(
            ),
        )
    }

    override fun toString(): String {
        return this.toYamlString()
    }

    // hint:A0E5F382
    override fun mergeEntity(other: Entity?): OpenIdConnectSecurityScheme {
        return when (other) {
            null ->
                this

            is OpenIdConnectSecurityScheme ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: SecurityScheme?): OpenIdConnectSecurityScheme {
        if (other == null) return this
        if (other !is OpenIdConnectSecurityScheme) TODO()
        return this
            .mergeOpenIdConnectUrlField(other._openIdConnectUrl)
            .mergeDescriptionField(other._description)
            .mergeExtensionsField(other._extensions)
    }

    companion object : ReferenceParser<SecurityScheme> {

        class Serializer : EntitySerializer<SecurityScheme>(SecurityScheme::class.java, OpenIdConnectSecurityScheme)
        class Deserializer : EntityDeserializer<SecurityScheme>(SecurityScheme::class.java, OpenIdConnectSecurityScheme)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): SecurityScheme {
            return de.lancom.openapi.jackson.extensionParser(wrapper, ::parseWrapperWithExtensions)
        }

        // hint:2C0E94A7
        fun parseWrapperWithExtensions(wrapper: Wrapper, extensions: Field<Map<String, Extension?>>): OpenIdConnectSecurityScheme {
            return OpenIdConnectSecurityScheme(
                _openIdConnectUrl = wrapper["openIdConnectUrl"].getNullOrElse {
                    getSingle {
                        getSingle {
                            getString()
                        }
                    }
                },
                _description = wrapper["description"].getNullOrElse {
                    getSingle {
                        getSingle {
                            getString()
                        }
                    }
                },
                _extensions = extensions,
            )
        }
    }
}
