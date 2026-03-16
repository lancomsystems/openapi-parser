package de.lancom.openapi.parser.entity

import tools.jackson.databind.JsonNode

interface JsonEntity : Entity {
    val jsonNode: JsonNode
}
