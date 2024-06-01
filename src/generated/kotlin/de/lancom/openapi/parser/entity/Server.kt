/*****************************************************************************
**   C A U T I O N                                                          **
**   This file is auto-generated!                                           **
**   If you want to make changes, please see the README.md file.            **
**   Please do not edit this file directly!                                 **
*****************************************************************************/
package de.lancom.openapi.parser.entity

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.lancom.openapi.common.types.*
import de.lancom.openapi.common.util.*
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.jackson.EntityDeserializer
import de.lancom.openapi.parser.jackson.EntitySerializer
import de.lancom.openapi.parser.jackson.Parser
import de.lancom.openapi.parser.jackson.Wrapper

// hint:9A1BF04C
@Suppress("PropertyName")
@JsonSerialize(using = Server.Companion.Serializer::class)
@JsonDeserialize(using = Server.Companion.Deserializer::class)
data class Server(
    val _url: Field<String?> = Field.unset(),
    val _description: Field<String?> = Field.unset(),
    val _variables: Field<Map<String, ServerVariable?>?> = Field.unset(),
    val _extensions: Field<Map<String, Extension?>> = Field.unset(),
    val __field_order: Set<Fields> = Fields.all,
) : Entity {

    ///////////////////////
    //
    // url
    //
    ///////////////////////

    // hint:3A7F9B2E
    val url: String?
        get() = _url.orNull

    // hint:F0C48D71
    fun setUrlField(url: Field<String?>): Server {
        return copy(_url = url)
            .updateFields()
    }

    // hint:8E56A4D9
    fun updateUrlField(updater: (Field<String?>) -> Field<String?>): Server {
        return setUrlField(updater(_url))
    }

    // hint:B1D730FC
    fun updateUrl(updater: (String?) -> String?): Server {
        return updateUrlField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeUrlField(urlFieldToMerge: Field<String?>): Server {
        return mergeUrl(urlFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeUrl(urlToMerge: String?): Server {
        return if (urlToMerge == null) {
            this
        } else {
            val oldUrl = _url.orNull
            if (oldUrl == null) {
                setUrlField(Field(urlToMerge))
            } else {
                // hint:2F684DAC
                setUrl(urlToMerge)
            }
        }
    }

    // hint:87B3E19C
    fun setUrl(url: String?): Server {
        return setUrlField(Field(url))
    }

    // hint:D465F782
    fun unsetUrl(): Server {
        return setUrlField(Field.unset())
    }

    // hint:47C9A0F6
    fun addUrl(url: String): Server {
        if (this.url != null) {
            throw IllegalStateException("Field url of Entity Server is already set to '${this.url}', refused to add new value '$url'")
        }
        return setUrl(url)
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
    fun setDescriptionField(description: Field<String?>): Server {
        return copy(_description = description)
            .updateFields()
    }

    // hint:8E56A4D9
    fun updateDescriptionField(updater: (Field<String?>) -> Field<String?>): Server {
        return setDescriptionField(updater(_description))
    }

    // hint:B1D730FC
    fun updateDescription(updater: (String?) -> String?): Server {
        return updateDescriptionField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeDescriptionField(descriptionFieldToMerge: Field<String?>): Server {
        return mergeDescription(descriptionFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeDescription(descriptionToMerge: String?): Server {
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
    fun setDescription(description: String?): Server {
        return setDescriptionField(Field(description))
    }

    // hint:D465F782
    fun unsetDescription(): Server {
        return setDescriptionField(Field.unset())
    }

    // hint:47C9A0F6
    fun addDescription(description: String): Server {
        if (this.description != null) {
            throw IllegalStateException("Field description of Entity Server is already set to '${this.description}', refused to add new value '$description'")
        }
        return setDescription(description)
    }

    ///////////////////////
    //
    // variables
    //
    ///////////////////////

    // hint:3A7F9B2E
    val variables: Map<String, ServerVariable?>?
        get() = _variables.orNull

    // hint:F0C48D71
    fun setVariablesField(variables: Field<Map<String, ServerVariable?>?>): Server {
        return copy(_variables = variables)
            .updateFields()
    }

    // hint:8E56A4D9
    fun updateVariablesField(updater: (Field<Map<String, ServerVariable?>?>) -> Field<Map<String, ServerVariable?>?>): Server {
        return setVariablesField(updater(_variables))
    }

    // hint:B1D730FC
    fun updateVariables(updater: (Map<String, ServerVariable?>?) -> Map<String, ServerVariable?>?): Server {
        return updateVariablesField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeVariablesField(variablesFieldToMerge: Field<Map<String, ServerVariable?>?>): Server {
        return mergeVariables(variablesFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeVariables(variablesToMerge: Map<String, ServerVariable?>?): Server {
        return if (variablesToMerge == null) {
            this
        } else {
            val oldVariables = _variables.orNull
            if (oldVariables == null) {
                setVariablesField(Field(variablesToMerge))
            } else {
                // hint:70A3D8B6
                setVariables(de.lancom.openapi.parser.util.mergeMap(oldVariables, variablesToMerge))
            }
        }
    }

    // hint:87B3E19C
    fun setVariables(variables: Map<String, ServerVariable?>?): Server {
        return setVariablesField(Field(variables))
    }

    // hint:D465F782
    fun unsetVariables(): Server {
        return setVariablesField(Field.unset())
    }

    // hint:5C81D396
    fun addVariables(variables: Map<String, ServerVariable?>?): Server {
        return mergeVariables(variables)
    }

    // hint:1A6B37F8
    fun addVariables(vararg variables: Pair<String, ServerVariable?>): Server {
        return addVariables(variables.toMap())
    }

    // hint:9D0E4CA5
    fun addVariable(key: String, value: ServerVariable?): Server {
        return addVariables(key to value)
    }

    // hint:B8F25E73
    fun addVariable(pair: Pair<String, ServerVariable?>): Server {
        return addVariables(mapOf(pair))
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
    fun setExtensionsField(extensions: Field<Map<String, Extension?>>): Server {
        return copy(_extensions = extensions)
            .updateFields()
    }

    // hint:8E56A4D9
    fun updateExtensionsField(updater: (Field<Map<String, Extension?>>) -> Field<Map<String, Extension?>>): Server {
        return setExtensionsField(updater(_extensions))
    }

    // hint:B1D730FC
    fun updateExtensions(updater: (Map<String, Extension?>) -> Map<String, Extension?>): Server {
        return updateExtensionsField { field ->
            field.flatMap { value ->
                Field(updater(value))
            }
        }
    }

    // hint:6542E98A
    fun mergeExtensionsField(extensionsFieldToMerge: Field<Map<String, Extension?>>): Server {
        return mergeExtensions(extensionsFieldToMerge.orNull)
    }

    // hint:A8BC6F23
    fun mergeExtensions(extensionsToMerge: Map<String, Extension?>?): Server {
        return if (extensionsToMerge == null) {
            this
        } else {
            val oldExtensions = _extensions.orNull
            if (oldExtensions == null) {
                setExtensionsField(Field(extensionsToMerge))
            } else {
                // hint:70A3D8B6
                setExtensions(de.lancom.openapi.parser.util.mergeMap(oldExtensions, extensionsToMerge))
            }
        }
    }

    // hint:87B3E19C
    fun setExtensions(extensions: Map<String, Extension?>): Server {
        return setExtensionsField(Field(extensions))
    }

    // hint:D465F782
    fun unsetExtensions(): Server {
        return setExtensionsField(Field.unset())
    }

    // hint:5C81D396
    fun addExtensions(extensions: Map<String, Extension?>): Server {
        return mergeExtensions(extensions)
    }

    // hint:1A6B37F8
    fun addExtensions(vararg extensions: Pair<String, Extension?>): Server {
        return addExtensions(extensions.toMap())
    }

    // hint:9D0E4CA5
    fun addExtension(key: String, value: Extension?): Server {
        return addExtensions(key to value)
    }

    // hint:B8F25E73
    fun addExtension(pair: Pair<String, Extension?>): Server {
        return addExtensions(mapOf(pair))
    }

    // hint:6A81E3FD
    override val entityDescriptor: EntityDescriptor by lazy {
        EntityDescriptor(
            entity = this,
            jsonNode = null,
            map = mapOf(
                "url" to _url,
                "description" to _description,
                "variables" to _variables,
            ),
            flatMap = listOf(
                _extensions,
            ),
            flatten = listOf(
            ),
            order = __field_order.map(Fields::value),
        )
    }

    // hint:49FE6A6F
    override fun toString(): String {
        return this.toYamlString(ignoreOrder = false)
    }

    // hint:20011A4E
    fun getFields(): Set<Fields> {
        val allFields: Set<Fields> = setOfNotNull(
            setOfNotNull<Fields>(
                if (_url.isDefined) Fields.url else null,
                if (_description.isDefined) Fields.description else null,
                if (_variables.isDefined) Fields.variables else null,
            ),
            _extensions.getFields()?.map(::Fields)?.toSet(),
        ).flatten().toSet()
        // keep order
        return Fields.cleanupFields(__field_order.filter(allFields::contains).toSet() + allFields)
    }

    // hint:EFD0D818
    fun updateFields(): Server {
        return copy(__field_order = getFields())
    }

    // hint:A0E5F382
    override fun mergeEntity(other: Entity?): Server {
        return when (other) {
            null ->
                this

            is Server ->
                merge(other)

            else ->
                TODO()
        }
    }

    // hint:D6150566
    fun mergeFields(other: Server): Server {
        return copy(
            __field_order = Fields.cleanupFields(this.__field_order + other.__field_order),
        ).updateFields()
    }

    // hint:716BFD54
    fun merge(other: Server?): Server {
        if (other == null) return this
        return this
            .mergeUrlField(other._url)
            .mergeDescriptionField(other._description)
            .mergeVariablesField(other._variables)
            .mergeExtensionsField(other._extensions)
            .mergeFields(other)
    }

    companion object : Parser<Server> {

        data class Fields(val value: String) {
            val set: Set<Fields> = setOf(this)

            companion object {
                val url = Fields("url")
                val description = Fields("description")
                val variables = Fields("variables")
                val all: Set<Fields> = setOf(
                    url,
                    description,
                    variables,
                )
                val skip: Set<Fields> = setOf(
                )

                fun cleanupFields(fields: Iterable<Fields>): Set<Fields> {
                    return fields.filterNot(skip::contains).toSet() + all
                }

                fun fromWrapper(wrapper: Wrapper): Set<Fields> {
                    return cleanupFields(wrapper.fieldOrder.map(::Fields))
                }
            }
        }

        class Serializer : EntitySerializer<Server>(Server::class.java, Server)
        class Deserializer : EntityDeserializer<Server>(Server::class.java, Server)

        // hint:5F72B6D8
        override fun parseWrapper(wrapper: Wrapper): Server {
            return de.lancom.openapi.parser.jackson.extensionParser(wrapper, ::parseWrapperWithExtensions)
        }

        // hint:2C0E94A7
        fun parseWrapperWithExtensions(
            wrapper: Wrapper,
            extensions: Field<Map<String, Extension?>>,
            fieldOrder: Set<String>,
        ): Server {
            return Server(
                _url = wrapper["url"].getNullOrElse {
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
                _variables = wrapper["variables"].getNullOrElse {
                    getMap {
                        getNullOrElse {
                            getSingle(ServerVariable::parseEntityOpt)
                        }
                    }
                },
                _extensions = extensions,
                __field_order = fieldOrder.map(::Fields).toSet() + Fields.all,
            )
        }
    }
}