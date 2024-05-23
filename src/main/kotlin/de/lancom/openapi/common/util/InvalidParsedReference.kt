package de.lancom.openapi.common.util

data class InvalidParsedReference(
    override val ref: String?
) : ParsedReference
