package de.lancom.openapi.jackson

import com.fasterxml.jackson.databind.DeserializationContext

abstract class KeyDeserializer<K : Key>(
    val factory: (String) -> K,
) : com.fasterxml.jackson.databind.KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): Any {
        return factory(key!!)
    }
}
