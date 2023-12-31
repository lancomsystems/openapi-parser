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
@JsonSerialize(using = APIKeySecurityScheme.Companion.Serializer::class)
@JsonDeserialize(using = APIKeySecurityScheme.Companion.Deserializer::class)
data class APIKeySecurityScheme(
    val _name: Field<String?> = Field.unset(),
    val _in: Field<InApiKey?> = Field.unset(),
    val _description: Field<String?> = Field.unset(),
    val _extensions: Field<Map<String, Extension?>> = Field.unset(),
) : SecurityScheme {

    // hint:C89A3E1F
    override val type: SecuritySchemeType = SecuritySchemeType.apiKey

    ///////////////////////
    //
    // name
    //
    ///////////////////////

    // hint:3A7F9B2E
    val name: String?
        get() = _name.orNull

    // hint:F0C48D71
    fun setNameField(name: Field<String?>): APIKeySecurityScheme {
        return copy(_name = name)
    }

    // hint:8E56A4D9
    fun updateNameField(updater: (Field<String?>) -> Field<String?>): APIKeySecurityScheme {
        return setNameField(updater(_name))
    }

    // hint:B1D730FC
    fun updateName(updater: (String?) -> String?): APIKeySecurityScheme {
        return updateNameField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeNameField(nameFieldToMerge: Field<String?>): APIKeySecurityScheme {
        return mergeName(nameFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeName(nameToMerge: String?): APIKeySecurityScheme {
        return if (nameToMerge == null) {
            this
        } else {
            val oldName = _name.orNull
            if (oldName == null) {
                setNameField(Field(nameToMerge))
            } else {
                // hint:2F684DAC
                setName(nameToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setName(name: String?): APIKeySecurityScheme {
        return setNameField(Field(name))
    }

    // hint:D465F782
    fun unsetName(): APIKeySecurityScheme {
        return setNameField(Field.unset())
    }

    // hint:47C9A0F6
    fun addName(name: String): APIKeySecurityScheme {
        if (this.name != null) {
            throw IllegalStateException("Field name of Entity APIKeySecurityScheme is already set to '${this.name}', refused to add new value '$name'")
        }
        return setName(name)
    }

    ///////////////////////
    //
    // in
    //
    ///////////////////////

    // hint:3A7F9B2E
    val `in`: InApiKey?
        get() = _in.orNull

    // hint:F0C48D71
    fun setInField(`in`: Field<InApiKey?>): APIKeySecurityScheme {
        return copy(_in = `in`)
    }

    // hint:8E56A4D9
    fun updateInField(updater: (Field<InApiKey?>) -> Field<InApiKey?>): APIKeySecurityScheme {
        return setInField(updater(_in))
    }

    // hint:B1D730FC
    fun updateIn(updater: (InApiKey?) -> InApiKey?): APIKeySecurityScheme {
        return updateInField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeInField(inFieldToMerge: Field<InApiKey?>): APIKeySecurityScheme {
        return mergeIn(inFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeIn(inToMerge: InApiKey?): APIKeySecurityScheme {
        return if (inToMerge == null) {
            this
        } else {
            val oldIn = _in.orNull
            if (oldIn == null) {
                setInField(Field(inToMerge))
            } else {
                // hint:2F684DAC
                setIn(inToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setIn(`in`: InApiKey?): APIKeySecurityScheme {
        return setInField(Field(`in`))
    }

    // hint:D465F782
    fun unsetIn(): APIKeySecurityScheme {
        return setInField(Field.unset())
    }

    // hint:47C9A0F6
    fun addIn(`in`: InApiKey): APIKeySecurityScheme {
        if (this.`in` != null) {
            throw IllegalStateException("Field in of Entity APIKeySecurityScheme is already set to '${this.`in`}', refused to add new value '$`in`'")
        }
        return setIn(`in`)
    }

    // hint:E3AF607D
    fun setInHeader(): APIKeySecurityScheme {
        return setIn(InApiKey.header)
    }

    // hint:3D98E6A5
    fun addInHeader(): APIKeySecurityScheme {
        return addIn(InApiKey.header)
    }

    // hint:E3AF607D
    fun setInQuery(): APIKeySecurityScheme {
        return setIn(InApiKey.query)
    }

    // hint:3D98E6A5
    fun addInQuery(): APIKeySecurityScheme {
        return addIn(InApiKey.query)
    }

    // hint:E3AF607D
    fun setInCookie(): APIKeySecurityScheme {
        return setIn(InApiKey.cookie)
    }

    // hint:3D98E6A5
    fun addInCookie(): APIKeySecurityScheme {
        return addIn(InApiKey.cookie)
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
    fun setDescriptionField(description: Field<String?>): APIKeySecurityScheme {
        return copy(_description = description)
    }

    // hint:8E56A4D9
    fun updateDescriptionField(updater: (Field<String?>) -> Field<String?>): APIKeySecurityScheme {
        return setDescriptionField(updater(_description))
    }

    // hint:B1D730FC
    fun updateDescription(updater: (String?) -> String?): APIKeySecurityScheme {
        return updateDescriptionField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDescriptionField(descriptionFieldToMerge: Field<String?>): APIKeySecurityScheme {
        return mergeDescription(descriptionFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDescription(descriptionToMerge: String?): APIKeySecurityScheme {
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
    fun setDescription(description: String?): APIKeySecurityScheme {
        return setDescriptionField(Field(description))
    }

    // hint:D465F782
    fun unsetDescription(): APIKeySecurityScheme {
        return setDescriptionField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDescription(description: String): APIKeySecurityScheme {
        if (this.description != null) {
            throw IllegalStateException("Field description of Entity APIKeySecurityScheme is already set to '${this.description}', refused to add new value '$description'")
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
    fun setExtensionsField(extensions: Field<Map<String, Extension?>>): APIKeySecurityScheme {
        return copy(_extensions = extensions)
    }

    // hint:8E56A4D9
    fun updateExtensionsField(updater: (Field<Map<String, Extension?>>) -> Field<Map<String, Extension?>>): APIKeySecurityScheme {
        return setExtensionsField(updater(_extensions))
    }

    // hint:B1D730FC
    fun updateExtensions(updater: (Map<String, Extension?>) -> Map<String, Extension?>): APIKeySecurityScheme {
        return updateExtensionsField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeExtensionsField(extensionsFieldToMerge: Field<Map<String, Extension?>>): APIKeySecurityScheme {
        return mergeExtensions(extensionsFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeExtensions(extensionsToMerge: Map<String, Extension?>?): APIKeySecurityScheme {
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
    fun setExtensions(extensions: Map<String, Extension?>): APIKeySecurityScheme {
        return setExtensionsField(Field(extensions))
    }

    // hint:D465F782
    fun unsetExtensions(): APIKeySecurityScheme {
        return setExtensionsField(Field.unset())
    }

    // hint:5C81D396
    fun addExtensions(extensions: Map<String, Extension?>): APIKeySecurityScheme {
        return mergeExtensions(extensions)
    }

    // hint:1A6B37F8
    fun addExtensions(vararg extensions: Pair<String, Extension?>): APIKeySecurityScheme {
        return addExtensions(extensions.toMap())
    }

    // hint:9D0E4CA5
    fun addExtension(key: String, value: Extension?): APIKeySecurityScheme {
        return addExtensions(key to value)
    }

    // hint:B8F25E73
    fun addExtension(pair: Pair<String, Extension?>): APIKeySecurityScheme {
        return addExtensions(mapOf(pair))
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = null,
            map = mapOf(
                "type" to Field(type),
                "name" to _name,
                "in" to _in,
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
    override fun mergeEntity(other: Entity?): APIKeySecurityScheme {
        return when (other) {
            null ->
                this

            is APIKeySecurityScheme ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: SecurityScheme?): APIKeySecurityScheme {
        if (other == null) return this
        if (other !is APIKeySecurityScheme) TODO()
        return this
            .mergeNameField(other._name)
            .mergeInField(other._in)
            .mergeDescriptionField(other._description)
            .mergeExtensionsField(other._extensions)
    }

    companion object : ReferenceParser<SecurityScheme> {

        class Serializer : EntitySerializer<SecurityScheme>(SecurityScheme::class.java, APIKeySecurityScheme)
        class Deserializer : EntityDeserializer<SecurityScheme>(SecurityScheme::class.java, APIKeySecurityScheme)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): SecurityScheme {
            return de.lancom.openapi.jackson.extensionParser(wrapper, ::parseWrapperWithExtensions)
        }

        // hint:2C0E94A7
        fun parseWrapperWithExtensions(wrapper: Wrapper, extensions: Field<Map<String, Extension?>>): APIKeySecurityScheme {
            return APIKeySecurityScheme(
                _name = wrapper["name"].getNullOrElse {
                    getSingle {
                        getSingle {
                            getString()
                        }
                    }
                },
                _in = wrapper["in"].getNullOrElse {
                    getSingle {
                        getSingle {
                            getEnum(InApiKey::valueOf)
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
