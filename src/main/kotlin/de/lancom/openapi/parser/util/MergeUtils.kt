package de.lancom.openapi.parser.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.readValue
import de.lancom.openapi.common.util.OperationMethod
import de.lancom.openapi.common.util.toYamlString
import de.lancom.openapi.common.util.yamlMapper
import de.lancom.openapi.parser.entity.*
import de.lancom.openapi.parser.field.Field
import de.lancom.openapi.parser.ref.ReferenceOrInstance
import de.lancom.openapi.parser.ref.Referenceable

private data class Rename(
    val component: String,
    val name: String,
    val suffix: String,
) {
    private val path = "#/components/$component/$name"

    fun renameYaml(subject: String): String {
        return subject.replace("'$path'", "'$path$suffix'")
    }

    fun renameComponent(openApi: OpenApi): OpenApi {
        return openApi.updateComponents { components: Components? ->
            components?.let(::renameComponent)
        }
    }

    fun <R : Referenceable> renameComponent(
        map: Map<String, ReferenceOrInstance<R>?>?
    ): Map<String, ReferenceOrInstance<R>?>? {
        if (map == null) {
            return null
        }
        val value = map[name] ?: return map
        return (map - name) + ("$name$suffix" to value)
    }

    fun renameComponent(components: Components): Components {
        return when (component) {
            "schemas" ->
                components.updateSchemas(::renameComponent)

            "responses" ->
                components.updateResponses(::renameComponent)

            "parameters" ->
                components.updateParameters(::renameComponent)

            "examples" ->
                components.updateExamples(::renameComponent)

            "requestBodies" ->
                components.updateRequestBodies(::renameComponent)

            "headers" ->
                components.updateHeaders(::renameComponent)

            "securitySchemes" ->
                components.updateSecuritySchemes(::renameComponent)

            "links" ->
                components.updateLinks(::renameComponent)

            "callbacks" ->
                components.updateCallbacks(::renameComponent)

            else ->
                TODO()
        }
    }
}

private fun Components?.componentsToRename(
    other: Components?,
    suffix: String
): List<Rename> {
    if (this == null || other == null) {
        return emptyList()
    }
    return mapOf(
        "schemas" to Components::schemas,
        "responses" to Components::responses,
        "parameters" to Components::parameters,
        "examples" to Components::examples,
        "requestBodies" to Components::requestBodies,
        "headers" to Components::headers,
        "securitySchemes" to Components::securitySchemes,
        "links" to Components::links,
        "callbacks" to Components::callbacks,
    ).flatMap { (name, getter) ->
        val ownMap = getter(this) ?: emptyMap()

        val otherMap = getter(other) ?: emptyMap()

        ownMap.keys.intersect(otherMap.keys).mapNotNull { commonKey: String ->
            val ownValue: Any? = ownMap[commonKey]
            val otherValue: Any? = otherMap[commonKey]
            if (ownValue == otherValue) {
                null
            } else {
                Rename(name, commonKey, suffix)
            }
        }
    }
}

private inline fun <reified T : Any> T.editYaml(editor: (String) -> String): T {
    val oldYamlString = toYamlString(ignoreOrder = false)
    val newYamlString = editor(oldYamlString)
    return yamlMapper.readValue(newYamlString)
}

fun Map<String, OpenApi>.mergeOpenApi(
    default: OpenApi,
    patchTag: ((String, String) -> String)? = null,
    patchPath: ((String, String) -> String)? = null,
    patchOperationId: ((String, String) -> String)? = null,
    createTagGroups: Boolean = false,
): OpenApi {
    return toList().fold(default) { left, (name, right) ->
        val prefixed = if (patchPath != null) {
            right.patchPaths(name, patchPath)
        } else {
            right
        }
        // rename schemas in prefixes
        val renames: List<Rename> = left.components.componentsToRename(prefixed.components, "_$name")
        val renamed = renames.fold(prefixed) { openApi, rename ->
            rename.renameComponent(openApi)
        }.editYaml { oldYaml: String ->
            renames.fold(oldYaml) { subject: String, rename: Rename ->
                rename.renameYaml(subject)
            }
        }.let { renamed ->
            if (patchTag != null) {
                renamed.patchTags(name, patchTag)
            } else {
                renamed
            }
        }.let { renamed ->
            if (patchOperationId != null) {
                renamed.prefixOperationIds(name, patchOperationId)
            } else {
                renamed
            }
        }.addRedocTagGroups(name, createTagGroups)
        left.merge(renamed)
    }.copy(_info = default._info).updateFields() // keep default info
}

private fun OpenApi.addRedocTagGroups(service: String, createTagGroups: Boolean): OpenApi {
    val usedTags =
        paths?.operations?.mapNotNull(Operation::tags)?.flatten()?.filterIsInstance<String>()?.toSet() ?: emptySet()
    val definedTags = tags?.filterNotNull()?.mapNotNull(Tag::name)?.toSet() ?: emptySet()
    val allTags = usedTags + definedTags
    return if (createTagGroups && allTags.isNotEmpty()) {
        val extName = "x-tagGroups"
        val tagGroupsExtension = extensions[extName] as? TagGroupsExtension
            ?: TagGroupsExtension()
        val updated = tagGroupsExtension
            .addTags(
                TagGroupsExtensionEntry()
                    .setName(service)
                    .addTags(allTags.toList())
            )
        addExtension(extName, updated)
    } else {
        this
    }
}

private fun OpenApi.prefixOperationIds(name: String, prefixOperationId: ((String, String) -> String)): OpenApi {
    return updatePaths { paths: Paths? ->
        paths?.updatePathItems { pathItems: Map<String, PathItem?> ->
            pathItems.mapValues { (_, pathItem: PathItem?) ->
                pathItem?.updateOperations { operation: Operation? ->
                    operation?.updateOperationId { operationId: String? ->
                        if (operationId == null) {
                            null
                        } else {
                            prefixOperationId(name, operationId)
                        }
                    }
                }
            }
        }
    }
}

private fun OpenApi.patchTags(name: String, patchTag: ((String, String) -> String)): OpenApi {
    return updateTags { tags ->
        tags?.map { tag ->
            if (tag == null) {
                null
            } else {
                val displayName = tag.extensions["x-displayName"]
                val withExtension = if (displayName == null) {
                    val jsonNode = yamlMapper.valueToTree<JsonNode>(tag.name)
                    tag.addExtension("x-displayName", RawExtension(Field(jsonNode)))
                } else {
                    tag
                }
                val tagName = tag.name
                if (tagName == null) {
                    withExtension
                } else {
                    withExtension.setName(patchTag(name, tagName))
                }
            }
        }
    }.updatePaths { paths: Paths? ->
        paths?.updatePathItems { pathItems: Map<String, PathItem?> ->
            pathItems.mapValues { (_, pathItem: PathItem?) ->
                pathItem?.updateOperations { operation: Operation? ->
                    operation?.updateTags { tags: List<String?>? ->
                        tags?.mapNotNull { tag ->
                            if (tag == null) {
                                null
                            } else {
                                patchTag(name, tag)
                            }
                        }
                    }
                }
            }
        }
    }
}

private val Paths.operations: List<Operation>
    get() = pathItems.values.filterNotNull().flatMap { pathItem ->
        pathItem.operations.values
    }

val PathItem.operations: Map<OperationMethod, Operation>
    get() = mapOf(
        OperationMethod.Get to get,
        OperationMethod.Put to put,
        OperationMethod.Post to post,
        OperationMethod.Delete to delete,
        OperationMethod.Options to options,
        OperationMethod.Head to head,
        OperationMethod.Patch to patch,
        OperationMethod.Trace to trace,
    ).mapNotNull { (type, operation) ->
        if (operation == null) {
            null
        } else {
            type to operation
        }
    }.toMap()

private fun OpenApi.patchPaths(name: String, patchPath: ((String, String) -> String)): OpenApi {
    return updatePaths { p: Paths? ->
        p?.updatePathItems { pi: Map<String, PathItem?> ->
            pi.mapKeys { (path, _) ->
                patchPath(name, path)
            }
        }
    }
}
