package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.BrewerInventory
import org.bukkit.potion.PotionType
import util.Objective
import util.matches
import util.contains

class BrewListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val potionType: PotionType
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        val inventory = event.inventory
        if (inventory is BrewerInventory && event.player matches player && potionType in inventory)
            updateObjectiveStatus()
    }

    @EventHandler
    fun onBrew(event: BrewEvent) {
        if (event.contents.viewers.any { it matches player } && potionType in event.results)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(potionType: PotionType) = { state: State, objective: Objective, player: Player ->
            BrewListener(state, objective, player, potionType)
        }
    }
}
