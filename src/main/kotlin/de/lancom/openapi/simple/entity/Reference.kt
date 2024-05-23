package de.lancom.openapi.simple.entity

import de.lancom.openapi.common.types.ComponentType
import de.lancom.openapi.common.util.InvalidParsedReference
import de.lancom.openapi.common.util.ParsedReference
import de.lancom.openapi.common.util.ValidParsedReference

sealed interface Reference : ComponentOrRef {
    val parsedReference: ParsedReference

    companion object {
        operator fun invoke(reference: String): Reference {
            return Reference(ParsedReference(reference))
        }

        operator fun invoke(componentType: ComponentType, name: String): Reference {
            return Reference(ValidParsedReference(componentType, name))
        }

        operator fun invoke(parsedReference: ParsedReference): Reference {
            return when (parsedReference) {
                is ValidParsedReference ->
                    Reference(parsedReference)

                is InvalidParsedReference ->
                    InvalidReference(parsedReference)
            }
        }

        operator fun invoke(validParsedReference: ValidParsedReference): Reference {
            return when (validParsedReference.componentType) {
                ComponentType.Schemas ->
                    SchemaReference(validParsedReference)

                ComponentType.Responses ->
                    ResponseReference(validParsedReference)

                ComponentType.Parameters ->
                    ParameterReference(validParsedReference)

                ComponentType.Examples ->
                    ExampleReference(validParsedReference)

                ComponentType.RequestBodies ->
                    RequestBodyReference(validParsedReference)

                ComponentType.Headers ->
                    HeaderReference(validParsedReference)

                ComponentType.SecuritySchemes ->
                    SecuritySchemeReference(validParsedReference)

                ComponentType.Links ->
                    LinkReference(validParsedReference)

                ComponentType.Callbacks ->
                    CallbackReference(validParsedReference)

                ComponentType.PathItems ->
                    PathItemReference(validParsedReference)

                ComponentType.Operations ->
                    OperationReference(validParsedReference)

                ComponentType.Servers ->
                    de.lancom.openapi.simple.entity.ServerReference(validParsedReference)
            }
        }
    }
}
