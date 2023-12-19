package de.lancom.openapi.view

data class NameView(
    val lowercase: String,
) {
    val singular: NameView = if (lowercase.endsWith("ies")) {
        NameView(lowercase.dropLast(3) + "y")
    } else if (lowercase.endsWith("s")) {
        NameView(lowercase.dropLast(1))
    } else {
        this
    }
    val underscore: String = "_$lowercase"

    val uppercase: String = lowercase.replaceFirstChar(Char::uppercase)

    val escaped: String = if (escape.contains(lowercase)) {
        "`$lowercase`"
    } else {
        lowercase
    }

    companion object {
        private val escape = setOf("in", "object")
    }
}
