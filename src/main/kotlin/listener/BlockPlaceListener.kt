package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockPlaceEvent
import util.Objective
import util.matches

class BlockPlaceListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val block: Material
) : ObjectiveListener<BlockPlaceEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: BlockPlaceEvent) = event.block.type == block && event.player matches player

    companion object {
        fun factory(block: Material) = { state: State, objective: Objective, player: Player ->
            BlockPlaceListener(state, objective, player, block)
        }
    }
}
