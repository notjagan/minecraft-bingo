package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemHeldEvent
import util.Objective

class ItemInInventoryListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val material: Material,
    private val amount: Int
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onItemHeldEvent(event: PlayerItemHeldEvent) {
        if (event.player.name == player.name && event.player.inventory.contains(material, amount))
            updateObjectiveStatus()
    }

    companion object {
        fun factory(material: Material, amount: Int) = { state: State, objective: Objective, player: Player ->
            ItemInInventoryListener(state, objective, player, material, amount)
        }
    }
}
