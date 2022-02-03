package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent
import util.Objective
import util.matches

class CraftListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val material: Material
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onCraft(event: CraftItemEvent) {
        if (event.recipe.result.type == material && event.whoClicked matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(material: Material) = { state: State, objective: Objective, player: Player ->
            CraftListener(state, objective, player, material)
        }
    }
}