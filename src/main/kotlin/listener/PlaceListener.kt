package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import util.Objective
import util.matches

class PlaceListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val block: Material
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        if (event.block.type == block && event.player matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(block: Material) = { state: State, objective: Objective, player: Player ->
            PlaceListener(state, objective, player, block)
        }
    }
}
