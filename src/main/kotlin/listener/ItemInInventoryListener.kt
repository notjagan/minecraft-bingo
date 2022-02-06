package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemHeldEvent
import util.Objective
import util.matches

class ItemInInventoryListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val material: Material,
    private val amount: Int
) : ObjectiveListener<PlayerItemHeldEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: PlayerItemHeldEvent) = event.player matches player && event.player.inventory.contains(material, amount)

    companion object {
        fun factory(material: Material, amount: Int) = { state: State, objective: Objective, player: Player ->
            ItemInInventoryListener(state, objective, player, material, amount)
        }

        fun factory(material: Material) = { state: State, objective: Objective, player: Player ->
            ItemInInventoryListener(state, objective, player, material, 1)
        }
    }
}
