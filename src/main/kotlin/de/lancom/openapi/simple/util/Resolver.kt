package de.lancom.openapi.simple.util

import de.lancom.openapi.simple.entity.*

interface Resolver {
    fun resolve(schemaOrRef: SchemaOrRef): Schema
    fun resolve(responseOrRef: ResponseOrRef): Response
    fun resolve(parameterOrRef: ParameterOrRef): Parameter
    fun resolve(exampleOrRef: ExampleOrRef): Example
    fun resolve(requestBodyOrRef: RequestBodyOrRef): RequestBody
    fun resolve(headerOrRef: HeaderOrRef): Header
    fun resolve(securitySchemeOrRef: SecuritySchemeOrRef): SecurityScheme
    fun resolve(linkOrRef: LinkOrRef): Link
    fun resolve(callbackOrRef: CallbackOrRef): Callback
}
