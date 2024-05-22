package de.lancom.openapi.simple.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import de.lancom.openapi.simple.types.Extension
import de.lancom.openapi.simple.util.Extensions
import de.lancom.openapi.simple.util.Resolver

data class Components(
    val schemas: Map<SchemaReference, SchemaOrRef> = emptyMap(),
    val responses: Map<ResponseReference, ResponseOrRef> = emptyMap(),
    val parameters: Map<ParameterReference, ParameterOrRef> = emptyMap(),
    val examples: Map<ExampleReference, ExampleOrRef> = emptyMap(),
    val requestBodies: Map<RequestBodyReference, RequestBodyOrRef> = emptyMap(),
    val headers: Map<HeaderReference, HeaderOrRef> = emptyMap(),
    val securitySchemes: Map<SecuritySchemeReference, SecuritySchemeOrRef> = emptyMap(),
    val links: Map<LinkReference, LinkOrRef> = emptyMap(),
    val callbacks: Map<CallbackReference, CallbackOrRef> = emptyMap(),
    override val extensions: Map<String, Extension> = emptyMap(),
) : Extensions, Resolver {
    @JsonIgnore
    val components: Map<Reference, ComponentOrRef> =
        schemas + responses + parameters + examples + requestBodies + headers + securitySchemes + links + callbacks

    fun addSchemas(vararg schemas: Schema): Components {
        schemas.forEach { schema ->
            if (schema.componentReference == null) {
                throw IllegalArgumentException("missing component name for schema $schema")
            }
        }
        return copy(schemas = this.schemas + schemas.associateBy { schema ->
            schema.componentReference!!
        })
    }

    fun addSchemas(schemas: Map<SchemaReference, SchemaOrRef>): Components {
        return copy(schemas = this.schemas + schemas)
    }

    fun addSchemas(vararg schemas: Pair<SchemaReference, SchemaOrRef>): Components {
        return addSchemas(schemas.toMap())
    }

    override fun resolve(schemaOrRef: SchemaOrRef): Schema {
        return resolveInternal(schemaOrRef, schemas::get, ::resolve)
    }

    override fun resolve(responseOrRef: ResponseOrRef): Response {
        return resolveInternal(responseOrRef, responses::get, ::resolve)
    }

    override fun resolve(parameterOrRef: ParameterOrRef): Parameter {
        return resolveInternal(parameterOrRef, parameters::get, ::resolve)
    }

    override fun resolve(exampleOrRef: ExampleOrRef): Example {
        return resolveInternal(exampleOrRef, examples::get, ::resolve)
    }

    override fun resolve(requestBodyOrRef: RequestBodyOrRef): RequestBody {
        return resolveInternal(requestBodyOrRef, requestBodies::get, ::resolve)
    }

    override fun resolve(headerOrRef: HeaderOrRef): Header {
        return resolveInternal(headerOrRef, headers::get, ::resolve)
    }

    override fun resolve(securitySchemeOrRef: SecuritySchemeOrRef): SecurityScheme {
        return resolveInternal(securitySchemeOrRef, securitySchemes::get, ::resolve)
    }

    override fun resolve(linkOrRef: LinkOrRef): Link {
        return resolveInternal(linkOrRef, links::get, ::resolve)
    }

    override fun resolve(callbackOrRef: CallbackOrRef): Callback {
        return resolveInternal(callbackOrRef, callbacks::get, ::resolve)
    }

    private inline fun <
            reified OrRef : ComponentOrRef,
            reified R : Reference,
            reified C : Component<C, R>
            > resolveInternal(
        orRef: OrRef,
        get: (R) -> OrRef?,
        resolve: (OrRef) -> C,
    ): C {
        return when (orRef) {
            is C ->
                orRef

            is R ->
                get(orRef)?.let(resolve)
                    ?: throw IllegalArgumentException("missing schema reference ${orRef.parsedReference.ref}")

            else ->
                throw IllegalArgumentException("unsupported schema reference $orRef")
        }
    }
}
