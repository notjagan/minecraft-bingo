package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import util.Objective
import util.matches

class BlockBreakListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val block: Material
) : ObjectiveListener<BlockBreakEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: BlockBreakEvent) = event.block.type == block && event.player matches player

    companion object {
        fun factory(block: Material) = { state: State, objective: Objective, player: Player ->
            BlockBreakListener(state, objective, player, block)
        }
    }
}
