package event

import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.KeyDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment

abstract class MetadataProperty<T>(val key: String, val default: T) {
    val mapper = jacksonObjectMapper()
    val defaultMetadata: String = mapper.writeValueAsString(default)

    class EnchantmentDeserializer : KeyDeserializer() {
        override fun deserializeKey(key: String, context: DeserializationContext) =
            Enchantment.getByKey(
                NamespacedKey.fromString(
                    Regex("""Enchantment\[(.*), .*]""").find(key)!!
                        .groupValues[1]
                )
            )!!
    }

    init {
        val module = SimpleModule()
        module.addKeyDeserializer(Enchantment::class.java, EnchantmentDeserializer())
        mapper.registerModule(module)
    }

    fun serialize(t: T): String = mapper.writeValueAsString(t)

    inline fun <reified R : T> deserialize(data: String): R = mapper.readValue(data)
}
