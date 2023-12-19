package de.lancom.openapi.codegen

fun unescape(key: String): String {
    return key.replace("`", "")
}

fun uppercase(key: String): String {
    return unescape(key).replaceFirstChar(Char::uppercaseChar)
}

fun singular(text: String): String {
    return when {
        text.endsWith("ies") ->
            text.dropLast(3) + "y"

        text.endsWith("s") ->
            text.dropLast(1)

        else ->
            text
    }
}
