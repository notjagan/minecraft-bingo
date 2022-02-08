package event

import org.bukkit.entity.Player
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.BlockInventoryHolder
import org.bukkit.inventory.BrewerInventory
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionType
import util.BrewHistory
import util.get
import util.set

@Suppress("unused")
class PlayerBrewEventEmitter(plugin: Plugin) : Emitter<PlayerBrewEvent>(
    plugin,
    Multiplier { event: BrewEvent ->
        val potions = event.results.mapNotNull {
            (it.itemMeta as? PotionMeta)?.basePotionData?.type
        }
        val metadata = event.block[key] ?: defaultMetadata
        val history = deserialize<BrewHistory>(metadata)
        history.addAll(potions)
        event.block[key] = serialize(history)
        event.contents.viewers.mapNotNull {
            (it as? Player)?.let { player -> PlayerBrewEvent(player, potions) }
        }
    },
    Multiplier { event: InventoryOpenEvent ->
        val holder = event.inventory.holder
        if (event.inventory is BrewerInventory && holder is BlockInventoryHolder) {
            val block = holder.block
            val metadata = block[key] ?: defaultMetadata
            val history = deserialize<BrewHistory>(metadata)
            listOfNotNull((event.player as? Player)?.let { PlayerBrewEvent(it, history) })
        } else
            listOf()
    }
) {
    companion object : MetadataProperty<BrewHistory>("history", hashSetOf())
}
