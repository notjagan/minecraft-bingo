package event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

abstract class MetadataProperty<T>(val key: String, val default: T) {
    protected val mapper = jacksonObjectMapper()
    val defaultMetadata: String = mapper.writeValueAsString(default)

    fun serialize(t: T): String = mapper.writeValueAsString(t)

    protected inline fun <reified R : T> deserialize(data: String): R = mapper.readValue(data)
}
