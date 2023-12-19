package de.lancom.openapi.view

import de.lancom.openapi.codegen.field.Field
import de.lancom.openapi.codegen.field.fieldExtension
import de.lancom.openapi.codegen.type.EntityType
import de.lancom.openapi.codegen.type.Type

class OpenApiEntity(
    val entityType: EntityType,
    val name: String? = null,
    val baseType: String? = null,
    val companion: Boolean = true,
    val fields: List<FieldView>,
    val extensions: Boolean = true,
    val unsetField: String? = null,
    val emptyYaml: String = "{}",
    val emptySubject: String? = null,
) {
    fun toTestView(): TestView {
        return TestView(
            entityName = name ?: entityType.name,
            emptyYaml = emptyYaml,
            emptySubject = emptySubject,
        )
    }

    fun toView(): DataClassView {
        val interfaceImports: List<String> = when {
            baseType == null && entityType.referenceable ->
                listOf(
                    "de.lancom.openapi.refs.Referenceable",
                )

            else ->
                emptyList()
        }

        val companionImports: List<String> = when {
            !companion ->
                emptyList()

            entityType.referenceable ->
                listOf(
                    "de.lancom.openapi.jackson.ReferenceParser",
                )

            else ->
                listOf(
                    "de.lancom.openapi.jackson.Parser",
                )
        }

        val referenceImports = if (baseType == null && entityType.referenceable) {
            listOf(
                "de.lancom.openapi.jackson.ReferenceParser",
            )
        } else {
            emptyList()
        }

        val typeImports = fields
            .map(FieldView::field)
            .map(Field::type)
            .flatMap(Type::imports)

        val staticCompanionImports = if (companion) {
            staticCompanionImports
        } else {
            emptyList()
        }

        val imports = listOf(
            referenceImports,
            interfaceImports,
            typeImports,
            staticImports,
            companionImports,
            staticCompanionImports,
        ).flatten().toSet().sorted().filterNot { import ->
            import.startsWith("de.lancom.openapi.entity.")
        }

        val `interface`: String = when {
            baseType != null ->
                baseType

            entityType.referenceable ->
                "Referenceable"

            else ->
                "Entity"
        }

        val fieldsWithExtensions: List<FieldView> = fields + if (extensions) {
            listOf(extensionsView)
        } else {
            emptyList()
        }

        val entityName = name ?: entityType.name

        val companionType = if (companion) {
            entityName
        } else {
            baseType ?: entityName
        }

        val companionView = if (companion) {
            DataClassCompanionView(
                hasJsonNode = fieldsWithExtensions.jsonNode != null,
                fields = fields,
                extensions = extensions,
            )
        } else {
            null
        }

        return DataClassView(
            imports = imports,
            entityName = entityName,
            `interface` = `interface`,
            entityType = entityType,
            baseType = baseType,
            companionType = companionType,
            fields = fieldsWithExtensions,
            companion = companionView,
            unsetField = unsetField ?: "Field.unset()",
        )
    }

    companion object {
        val staticImports = listOf(
            "com.fasterxml.jackson.databind.annotation.JsonDeserialize",
            "com.fasterxml.jackson.databind.annotation.JsonSerialize",
            "de.lancom.openapi.field.Field",
            "de.lancom.openapi.tools.toYamlString",
        )

        val staticCompanionImports = listOf(
            "de.lancom.openapi.jackson.EntityDeserializer",
            "de.lancom.openapi.jackson.EntitySerializer",
            "de.lancom.openapi.jackson.Wrapper",
        )

        val extensionsView: FieldView = fieldExtension.map().flat().defaultEmptyMap().name("extensions")
    }
}
