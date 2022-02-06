package event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.BlockInventoryHolder
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin
import util.BrewingStandHistory

@Suppress("unused")
class PlayerBrewEventEmitter(plugin: Plugin) : Emitter<PlayerBrewEvent>(
    plugin,
    Propagator { event: BrewEvent ->
        val potions = event.results.mapNotNull {
            (it.itemMeta as? PotionMeta)?.basePotionData?.type
        }
        val metadata = event.block.getMetadata(plugin, KEY)
        val history = mapper.readValue<BrewingStandHistory>(metadata ?: emptyList)
        history.addAll(potions)
        event.block.setMetadata(KEY, FixedMetadataValue(plugin, mapper.writeValueAsString(history)))
        event.contents.viewers
            .mapNotNull { it as? Player }
            .map { player ->
                PlayerBrewEvent(player, potions)
            }
    },
    Propagator { event: InventoryOpenEvent ->
        val holder = event.inventory.holder
        run {
            if (holder is BlockInventoryHolder) {
                val block = holder.block
                val metadata = block.getMetadata(plugin, KEY) ?: return@run
                val history = mapper.readValue<BrewingStandHistory>(metadata)
                val player = event.player as? Player ?: return@run
                return@Propagator listOf(PlayerBrewEvent(player, history.potions))
            }
        }
        listOf()
    }
) {
    companion object {
        const val KEY = "history"
        val mapper = jacksonObjectMapper()
        val emptyList = mapper.writeValueAsString(BrewingStandHistory(hashSetOf()))!!

        fun Block.getMetadata(plugin: Plugin, key: String) = getMetadata(key)
            .firstNotNullOfOrNull {
                it.takeIf {
                    it.owningPlugin == plugin
                }
            }?.asString()
    }
}
