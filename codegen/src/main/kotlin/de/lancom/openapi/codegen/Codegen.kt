package de.lancom.openapi.codegen

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.Mustache
import de.lancom.openapi.codegen.type.EnumType
import de.lancom.openapi.codegen.view.EnumClassView
import java.io.File
import java.io.StringWriter

fun process(file: File, code: String) {
    file.parentFile.mkdirs()
    file.writeText(code)
    println("generated $file")
}

val globalVars = mapOf(
    "openbrace" to "{",
    "closebrace" to "}",
)

private val mustacheFactory = DefaultMustacheFactory()
private val mustacheTemplateDataClass = mustacheFactory.compile("templates/data-class.mustache")
private val mustacheTemplateEnumClass = mustacheFactory.compile("templates/enum-class.mustache")
private val mustacheTemplateTest = mustacheFactory.compile("templates/test.mustache")

fun <T> processTemplate(value: T, template: Mustache, file: File) {
    val sw = StringWriter()
    template.execute(sw, listOf(globalVars, value))
    sw.flush()
    process(
        file = file,
        code = sw.toString()
    )
}

fun main() {
    val basedir = "../src"
    listOf(
        "$basedir/generated/kotlin/de/lancom/openapi/parser/entity",
        "$basedir/generated/kotlin/de/lancom/openapi/common/types",
        "$basedir/generatedtest",
    ).forEach { dir ->
        File(dir).deleteRecursively()
    }
    allOpenApiEntities.forEach { entity ->
        val view = entity.toView()
        processTemplate(
            value = view,
            template = mustacheTemplateDataClass,
            file = File("$basedir/generated/kotlin/de/lancom/openapi/parser/entity/${view.entityName}.kt"),
        )
        val testView = entity.toTestView()
        processTemplate(
            value = testView,
            template = mustacheTemplateTest,
            file = File("$basedir/generatedtest/kotlin/de/lancom/openapi/parser/serialisation/${testView.entityName}SerialisationTest.kt"),
        )
    }
    EnumType.all.forEach { enumType ->
        processTemplate(
            value = EnumClassView(enumType),
            template = mustacheTemplateEnumClass,
            file = File("$basedir/generated/kotlin/de/lancom/openapi/common/types/${enumType.enum}.kt"),
        )
    }
}
