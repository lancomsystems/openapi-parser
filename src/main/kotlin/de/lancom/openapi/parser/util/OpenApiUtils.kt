package de.lancom.openapi.parser.util

import de.lancom.openapi.common.types.ContentType
import de.lancom.openapi.common.types.ResponseStatusCode
import de.lancom.openapi.parser.entity.*
import de.lancom.openapi.parser.ref.Instance
import de.lancom.openapi.parser.ref.ReferenceOrInstance

fun Response.addJsonContent(schema: Schema): Response {
    return addJsonContent(Instance(schema))
}

fun Response.addJsonContent(schema: ReferenceOrInstance<Schema>): Response {
    return addJsonContent(MediaType().addSchema(schema))
}

fun Response.addJsonContent(mediaType: MediaType): Response {
    return addContent(ContentType.applicationJson.contentType to mediaType)
}

fun RequestBody.addJsonContent(schema: Schema): RequestBody {
    return addJsonContent(Instance(schema))
}

fun RequestBody.addJsonContent(schema: ReferenceOrInstance<Schema>): RequestBody {
    return addJsonContent(MediaType().addSchema(schema))
}

fun RequestBody.addJsonContent(mediaType: MediaType): RequestBody {
    return addContent(ContentType.applicationJson.contentType to mediaType)
}

@JvmName("addResponsesInstance")
fun Operation.addResponses(responses: Map<ResponseStatusCode, Response>): Operation {
    val mapped = responses.mapValues { (_, value) ->
        Instance(value)
    }
    return addResponses(mapped)
}

@JvmName("addResponsesInstance")
fun Operation.addResponses(vararg response: Pair<ResponseStatusCode, Response>): Operation {
    return addResponses(response.toMap())
}

@JvmName("addResponsesInstance")
fun Operation.addResponse(response: Pair<ResponseStatusCode, Response>): Operation {
    return addResponses(response)
}

fun Operation.addResponses(responses: Map<ResponseStatusCode, ReferenceOrInstance<Response>?>): Operation {
    return mergeResponses(Responses().setResponses(responses))
}

fun Operation.addResponses(vararg response: Pair<ResponseStatusCode, ReferenceOrInstance<Response>?>): Operation {
    return addResponses(response.toMap())
}

fun Operation.addResponse(response: Pair<ResponseStatusCode, ReferenceOrInstance<Response>?>): Operation {
    return addResponses(response)
}

fun Components.addSchemas(schemas: Map<String, Schema>): Components {
    return addSchemas(schemas.mapValues { (_, schema) ->
        Instance(schema)
    })
}

fun PathItem?.updateOperations(updater: (Operation?) -> Operation?): PathItem? {
    return this
        ?.updateGet(updater)
        ?.updatePut(updater)
        ?.updatePost(updater)
        ?.updateDelete(updater)
        ?.updateOptions(updater)
        ?.updateHead(updater)
        ?.updatePatch(updater)
        ?.updateTrace(updater)
}
