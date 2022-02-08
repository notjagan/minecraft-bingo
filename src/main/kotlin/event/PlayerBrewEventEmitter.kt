package event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
    Propagator { event: BrewEvent ->
        val potions = event.results.mapNotNull {
            (it.itemMeta as? PotionMeta)?.basePotionData?.type
        }
        val metadata = event.block[KEY] ?: emptyList
        val history = mapper.readValue<BrewHistory>(metadata)
        history.addAll(potions)
        event.block[KEY] = mapper.writeValueAsString(history)
        event.contents.viewers.mapNotNull {
            (it as? Player)?.let { player -> PlayerBrewEvent(player, potions) }
        }
    },
    Propagator { event: InventoryOpenEvent ->
        val holder = event.inventory.holder
        if (event.inventory is BrewerInventory && holder is BlockInventoryHolder) {
            val block = holder.block
            val metadata = block[KEY] ?: emptyList
            val history = mapper.readValue<BrewHistory>(metadata)
            listOfNotNull((event.player as? Player)?.let { PlayerBrewEvent(it, history) })
        } else
            listOf()
    }
) {
    companion object {
        const val KEY = "history"
        val mapper = jacksonObjectMapper()
        val emptyList = mapper.writeValueAsString(hashSetOf<PotionType>())!!
    }
}
