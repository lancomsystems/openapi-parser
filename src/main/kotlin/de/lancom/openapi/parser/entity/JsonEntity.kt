package de.lancom.openapi.parser.entity

import com.fasterxml.jackson.databind.JsonNode

interface JsonEntity : Entity {
    val jsonNode: JsonNode
}
