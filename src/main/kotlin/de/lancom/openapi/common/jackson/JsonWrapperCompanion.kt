package de.lancom.openapi.common.jackson

abstract class JsonWrapperCompanion<JW : JsonWrapper>(
    private val factory: (String) -> JW?,
    private val clazz: Class<JW>,
) {
    abstract inner class AbstractSerializer : JsonWrapperSerializer<JW>(clazz)
    abstract inner class AbstractKeySerializer : JsonWrapperKeySerializer<JW>(clazz)
    abstract inner class AbstractDeserializer : JsonWrapperDeserializer<JW>(clazz, factory)
}
