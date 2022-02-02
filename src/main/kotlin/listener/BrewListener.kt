package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.BrewerInventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionType
import util.Objective

class BrewListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val potionType: PotionType
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        val inventory = event.inventory
        if (
            inventory is BrewerInventory &&
            event.player.uniqueId == player.uniqueId &&
            inventory.hasPotionType(potionType)
        )
            updateObjectiveStatus()
    }

    @EventHandler
    fun onBrew(event: BrewEvent) {
        if (event.contents.viewers.any { it.uniqueId == player.uniqueId } && event.results.hasPotionType(potionType))
            updateObjectiveStatus()
    }

    companion object {
        fun factory(potionType: PotionType) = { state: State, objective: Objective, player: Player ->
            BrewListener(state, objective, player, potionType)
        }
    }
}

fun Iterable<ItemStack>.hasPotionType(potionType: PotionType) = this.any { stack ->
    val meta = stack.itemMeta
    meta is PotionMeta && meta.basePotionData.type == potionType
}
