package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.InvalidParsedReference

data class InvalidReference(
    override val parsedReference: InvalidParsedReference,
) : Reference,
    CallbackOrRef,
    ComponentOrRef,
    ExampleOrRef,
    HeaderOrRef,
    LinkOrRef,
    OperationOrRef,
    ParameterOrRef,
    PathItemOrRef,
    RequestBodyOrRef,
    ResponseOrRef,
    SchemaOrRef,
    SecuritySchemeOrRef,
    de.lancom.openapi.simple.entity.ServerOrRef {
    override val componentType: ComponentType
        get() = throw UnsupportedOperationException("Invalid reference does not have a component type")
}
