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
import de.lancom.openapi.refs.Referenceable
import de.lancom.openapi.tools.toYamlString

// hint:9A1BF04C
@Suppress("PropertyName")
@JsonSerialize(using = RequestBody.Companion.Serializer::class)
@JsonDeserialize(using = RequestBody.Companion.Deserializer::class)
data class RequestBody(
    val _description: Field<String?> = Field.unset(),
    val _content: Field<Map<String, MediaType?>?> = Field.unset(),
    val _required: Field<Boolean> = Field.unset(),
    val _extensions: Field<Map<String, Extension?>> = Field.unset(),
) : Referenceable {

    ///////////////////////
    //
    // description
    //
    ///////////////////////

    // hint:3A7F9B2E
    val description: String?
        get() = _description.orNull

    // hint:F0C48D71
    fun setDescriptionField(description: Field<String?>): RequestBody {
        return copy(_description = description)
    }

    // hint:8E56A4D9
    fun updateDescriptionField(updater: (Field<String?>) -> Field<String?>): RequestBody {
        return setDescriptionField(updater(_description))
    }

    // hint:B1D730FC
    fun updateDescription(updater: (String?) -> String?): RequestBody {
        return updateDescriptionField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDescriptionField(descriptionFieldToMerge: Field<String?>): RequestBody {
        return mergeDescription(descriptionFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDescription(descriptionToMerge: String?): RequestBody {
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
    fun setDescription(description: String?): RequestBody {
        return setDescriptionField(Field(description))
    }

    // hint:D465F782
    fun unsetDescription(): RequestBody {
        return setDescriptionField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDescription(description: String): RequestBody {
        if (this.description != null) {
            throw IllegalStateException("Field description of Entity RequestBody is already set to '${this.description}', refused to add new value '$description'")
        }
        return setDescription(description)
    }

    ///////////////////////
    //
    // content
    //
    ///////////////////////

    // hint:3A7F9B2E
    val content: Map<String, MediaType?>?
        get() = _content.orNull

    // hint:F0C48D71
    fun setContentField(content: Field<Map<String, MediaType?>?>): RequestBody {
        return copy(_content = content)
    }

    // hint:8E56A4D9
    fun updateContentField(updater: (Field<Map<String, MediaType?>?>) -> Field<Map<String, MediaType?>?>): RequestBody {
        return setContentField(updater(_content))
    }

    // hint:B1D730FC
    fun updateContent(updater: (Map<String, MediaType?>?) -> Map<String, MediaType?>?): RequestBody {
        return updateContentField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeContentField(contentFieldToMerge: Field<Map<String, MediaType?>?>): RequestBody {
        return mergeContent(contentFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeContent(contentToMerge: Map<String, MediaType?>?): RequestBody {
        return if (contentToMerge == null) {
            this
        } else {
            val oldContent = _content.orNull
            if (oldContent == null) {
                setContentField(Field(contentToMerge))
            } else {
                // hint:70A3D8B6
                setContent(de.lancom.openapi.tools.mergeMap(oldContent, contentToMerge))
            }
        }
    }

    // hint:87B3E19C
    fun setContent(content: Map<String, MediaType?>?): RequestBody {
        return setContentField(Field(content))
    }

    // hint:D465F782
    fun unsetContent(): RequestBody {
        return setContentField(Field.unset())
    }

    // hint:5C81D396
    fun addContent(content: Map<String, MediaType?>?): RequestBody {
        return mergeContent(content)
    }

    // hint:1A6B37F8
    fun addContent(vararg content: Pair<String, MediaType?>): RequestBody {
        return addContent(content.toMap())
    }

    // hint:9D0E4CA5
    fun addContent(key: String, value: MediaType?): RequestBody {
        return addContent(key to value)
    }

    // hint:B8F25E73
    fun addContent(pair: Pair<String, MediaType?>): RequestBody {
        return addContent(mapOf(pair))
    }

    ///////////////////////
    //
    // required
    //
    ///////////////////////

    // hint:3A7F9B2E
    val required: Boolean
        get() = _required.orNull ?: false

    // hint:F0C48D71
    fun setRequiredField(required: Field<Boolean>): RequestBody {
        return copy(_required = required)
    }

    // hint:8E56A4D9
    fun updateRequiredField(updater: (Field<Boolean>) -> Field<Boolean>): RequestBody {
        return setRequiredField(updater(_required))
    }

    // hint:B1D730FC
    fun updateRequired(updater: (Boolean) -> Boolean): RequestBody {
        return updateRequiredField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeRequiredField(requiredFieldToMerge: Field<Boolean>): RequestBody {
        return mergeRequired(requiredFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeRequired(requiredToMerge: Boolean?): RequestBody {
        return if (requiredToMerge == null) {
            this
        } else {
            val oldRequired = _required.orNull
            if (oldRequired == null) {
                setRequiredField(Field(requiredToMerge))
            } else {
                // hint:2F684DAC
                setRequired(requiredToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setRequired(required: Boolean): RequestBody {
        return setRequiredField(Field(required))
    }

    // hint:D465F782
    fun unsetRequired(): RequestBody {
        return setRequiredField(Field.unset())
    }

    // hint:47C9A0F6
    fun addRequired(required: Boolean = true): RequestBody {
        return setRequired(required)
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
    fun setExtensionsField(extensions: Field<Map<String, Extension?>>): RequestBody {
        return copy(_extensions = extensions)
    }

    // hint:8E56A4D9
    fun updateExtensionsField(updater: (Field<Map<String, Extension?>>) -> Field<Map<String, Extension?>>): RequestBody {
        return setExtensionsField(updater(_extensions))
    }

    // hint:B1D730FC
    fun updateExtensions(updater: (Map<String, Extension?>) -> Map<String, Extension?>): RequestBody {
        return updateExtensionsField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeExtensionsField(extensionsFieldToMerge: Field<Map<String, Extension?>>): RequestBody {
        return mergeExtensions(extensionsFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeExtensions(extensionsToMerge: Map<String, Extension?>?): RequestBody {
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
    fun setExtensions(extensions: Map<String, Extension?>): RequestBody {
        return setExtensionsField(Field(extensions))
    }

    // hint:D465F782
    fun unsetExtensions(): RequestBody {
        return setExtensionsField(Field.unset())
    }

    // hint:5C81D396
    fun addExtensions(extensions: Map<String, Extension?>): RequestBody {
        return mergeExtensions(extensions)
    }

    // hint:1A6B37F8
    fun addExtensions(vararg extensions: Pair<String, Extension?>): RequestBody {
        return addExtensions(extensions.toMap())
    }

    // hint:9D0E4CA5
    fun addExtension(key: String, value: Extension?): RequestBody {
        return addExtensions(key to value)
    }

    // hint:B8F25E73
    fun addExtension(pair: Pair<String, Extension?>): RequestBody {
        return addExtensions(mapOf(pair))
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = null,
            map = mapOf(
                "description" to _description,
                "content" to _content,
                "required" to _required,
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
    override fun mergeEntity(other: Entity?): RequestBody {
        return when (other) {
            null ->
                this

            is RequestBody ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: RequestBody?): RequestBody {
        if (other == null) return this
        return this
            .mergeDescriptionField(other._description)
            .mergeContentField(other._content)
            .mergeRequiredField(other._required)
            .mergeExtensionsField(other._extensions)
    }

    companion object : ReferenceParser<RequestBody> {

        class Serializer : EntitySerializer<RequestBody>(RequestBody::class.java, RequestBody)
        class Deserializer : EntityDeserializer<RequestBody>(RequestBody::class.java, RequestBody)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): RequestBody {
            return de.lancom.openapi.jackson.extensionParser(wrapper, ::parseWrapperWithExtensions)
        }

        // hint:2C0E94A7
        fun parseWrapperWithExtensions(wrapper: Wrapper, extensions: Field<Map<String, Extension?>>): RequestBody {
            return RequestBody(
                _description = wrapper["description"].getNullOrElse {
                    getSingle {
                        getSingle {
                            getString()
                        }
                    }
                },
                _content = wrapper["content"].getNullOrElse {
                    getMap {
                        getNullOrElse {
                            getSingle(MediaType::parseEntityOpt)
                        }
                    }
                },
                _required = wrapper["required"].getSingle {
                    getSingle {
                        getSingle {
                            getBoolean()
                        }
                    }
                },
                _extensions = extensions,
            )
        }
    }
}
