package listener

import game.State
import org.bukkit.TreeType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.world.StructureGrowEvent
import util.Objective
import util.matches

class StructureGrowListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val species: TreeType
) : ObjectiveListener<StructureGrowEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: StructureGrowEvent) = event.species == species && event.player matches player

    companion object {
        fun factory(treeType: TreeType) = { state: State, objective: Objective, player: Player ->
            StructureGrowListener(state, objective, player, treeType)
        }
    }
}

