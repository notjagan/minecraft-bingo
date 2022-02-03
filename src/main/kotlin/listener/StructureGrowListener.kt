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
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onStructureGrow(event: StructureGrowEvent) {
        if (event.species == species && event.player matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(treeType: TreeType) = { state: State, objective: Objective, player: Player ->
            StructureGrowListener(state, objective, player, treeType)
        }
    }
}

