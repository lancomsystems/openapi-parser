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
import de.lancom.openapi.jackson.Parser
import de.lancom.openapi.jackson.Wrapper
import de.lancom.openapi.tools.toYamlString

// hint:9A1BF04C
@Suppress("PropertyName")
@JsonSerialize(using = ServerVariable.Companion.Serializer::class)
@JsonDeserialize(using = ServerVariable.Companion.Deserializer::class)
data class ServerVariable(
    val _enum: Field<List<String?>?> = Field.unset(),
    val _default: Field<String?> = Field.unset(),
    val _description: Field<String?> = Field.unset(),
    val _extensions: Field<Map<String, Extension?>> = Field.unset(),
) : Entity {

    ///////////////////////
    //
    // enum
    //
    ///////////////////////

    // hint:3A7F9B2E
    val enum: List<String?>?
        get() = _enum.orNull

    // hint:F0C48D71
    fun setEnumField(enum: Field<List<String?>?>): ServerVariable {
        return copy(_enum = enum)
    }

    // hint:8E56A4D9
    fun updateEnumField(updater: (Field<List<String?>?>) -> Field<List<String?>?>): ServerVariable {
        return setEnumField(updater(_enum))
    }

    // hint:B1D730FC
    fun updateEnum(updater: (List<String?>?) -> List<String?>?): ServerVariable {
        return updateEnumField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeEnumField(enumFieldToMerge: Field<List<String?>?>): ServerVariable {
        return mergeEnum(enumFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeEnum(enumToMerge: List<String?>?): ServerVariable {
        return if (enumToMerge == null) {
            this
        } else {
            val oldEnum = _enum.orNull
            if (oldEnum == null) {
                setEnumField(Field(enumToMerge))
            } else {
                // hint:19DE5C87
                setEnum((oldEnum + enumToMerge).distinct())
            }
        }
    }

    // hint:87B3E19C
    fun setEnum(enum: List<String?>?): ServerVariable {
        return setEnumField(Field(enum))
    }

    // hint:D465F782
    fun unsetEnum(): ServerVariable {
        return setEnumField(Field.unset())
    }

    // hint:3E9A8C01
    fun addEnum(enum: List<String?>?): ServerVariable {
        return setEnum((this.enum ?: emptyList()) + (enum ?: emptyList()))
    }

    // hint:F7420EB5
    fun addEnum(vararg enum: String): ServerVariable {
        return addEnum(enum.toList())
    }

    ///////////////////////
    //
    // default
    //
    ///////////////////////

    // hint:3A7F9B2E
    val default: String?
        get() = _default.orNull

    // hint:F0C48D71
    fun setDefaultField(default: Field<String?>): ServerVariable {
        return copy(_default = default)
    }

    // hint:8E56A4D9
    fun updateDefaultField(updater: (Field<String?>) -> Field<String?>): ServerVariable {
        return setDefaultField(updater(_default))
    }

    // hint:B1D730FC
    fun updateDefault(updater: (String?) -> String?): ServerVariable {
        return updateDefaultField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDefaultField(defaultFieldToMerge: Field<String?>): ServerVariable {
        return mergeDefault(defaultFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDefault(defaultToMerge: String?): ServerVariable {
        return if (defaultToMerge == null) {
            this
        } else {
            val oldDefault = _default.orNull
            if (oldDefault == null) {
                setDefaultField(Field(defaultToMerge))
            } else {
                // hint:2F684DAC
                setDefault(defaultToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setDefault(default: String?): ServerVariable {
        return setDefaultField(Field(default))
    }

    // hint:D465F782
    fun unsetDefault(): ServerVariable {
        return setDefaultField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDefault(default: String): ServerVariable {
        if (this.default != null) {
            throw IllegalStateException("Field default of Entity ServerVariable is already set to '${this.default}', refused to add new value '$default'")
        }
        return setDefault(default)
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
    fun setDescriptionField(description: Field<String?>): ServerVariable {
        return copy(_description = description)
    }

    // hint:8E56A4D9
    fun updateDescriptionField(updater: (Field<String?>) -> Field<String?>): ServerVariable {
        return setDescriptionField(updater(_description))
    }

    // hint:B1D730FC
    fun updateDescription(updater: (String?) -> String?): ServerVariable {
        return updateDescriptionField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDescriptionField(descriptionFieldToMerge: Field<String?>): ServerVariable {
        return mergeDescription(descriptionFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDescription(descriptionToMerge: String?): ServerVariable {
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
    fun setDescription(description: String?): ServerVariable {
        return setDescriptionField(Field(description))
    }

    // hint:D465F782
    fun unsetDescription(): ServerVariable {
        return setDescriptionField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDescription(description: String): ServerVariable {
        if (this.description != null) {
            throw IllegalStateException("Field description of Entity ServerVariable is already set to '${this.description}', refused to add new value '$description'")
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
    fun setExtensionsField(extensions: Field<Map<String, Extension?>>): ServerVariable {
        return copy(_extensions = extensions)
    }

    // hint:8E56A4D9
    fun updateExtensionsField(updater: (Field<Map<String, Extension?>>) -> Field<Map<String, Extension?>>): ServerVariable {
        return setExtensionsField(updater(_extensions))
    }

    // hint:B1D730FC
    fun updateExtensions(updater: (Map<String, Extension?>) -> Map<String, Extension?>): ServerVariable {
        return updateExtensionsField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeExtensionsField(extensionsFieldToMerge: Field<Map<String, Extension?>>): ServerVariable {
        return mergeExtensions(extensionsFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeExtensions(extensionsToMerge: Map<String, Extension?>?): ServerVariable {
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
    fun setExtensions(extensions: Map<String, Extension?>): ServerVariable {
        return setExtensionsField(Field(extensions))
    }

    // hint:D465F782
    fun unsetExtensions(): ServerVariable {
        return setExtensionsField(Field.unset())
    }

    // hint:5C81D396
    fun addExtensions(extensions: Map<String, Extension?>): ServerVariable {
        return mergeExtensions(extensions)
    }

    // hint:1A6B37F8
    fun addExtensions(vararg extensions: Pair<String, Extension?>): ServerVariable {
        return addExtensions(extensions.toMap())
    }

    // hint:9D0E4CA5
    fun addExtension(key: String, value: Extension?): ServerVariable {
        return addExtensions(key to value)
    }

    // hint:B8F25E73
    fun addExtension(pair: Pair<String, Extension?>): ServerVariable {
        return addExtensions(mapOf(pair))
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = null,
            map = mapOf(
                "enum" to _enum,
                "default" to _default,
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
    override fun mergeEntity(other: Entity?): ServerVariable {
        return when (other) {
            null ->
                this

            is ServerVariable ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:716BFD54
    fun merge(other: ServerVariable?): ServerVariable {
        if (other == null) return this
        return this
            .mergeEnumField(other._enum)
            .mergeDefaultField(other._default)
            .mergeDescriptionField(other._description)
            .mergeExtensionsField(other._extensions)
    }

    companion object : Parser<ServerVariable> {

        class Serializer : EntitySerializer<ServerVariable>(ServerVariable::class.java, ServerVariable)
        class Deserializer : EntityDeserializer<ServerVariable>(ServerVariable::class.java, ServerVariable)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): ServerVariable {
            return de.lancom.openapi.jackson.extensionParser(wrapper, ::parseWrapperWithExtensions)
        }

        // hint:2C0E94A7
        fun parseWrapperWithExtensions(wrapper: Wrapper, extensions: Field<Map<String, Extension?>>): ServerVariable {
            return ServerVariable(
                _enum = wrapper["enum"].getNullOrElse {
                    getList {
                        getNullOrElse {
                            getString()
                        }
                    }
                },
                _default = wrapper["default"].getNullOrElse {
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
