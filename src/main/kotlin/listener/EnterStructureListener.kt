package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerMoveEvent
import util.Objective
import util.StructureType
import util.contains
import util.matches

class EnterStructureListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val structureType: StructureType,
) : ObjectiveListener<PlayerMoveEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: PlayerMoveEvent) = event.player matches player && player in structureType

    companion object {
        fun factory(structureType: StructureType) = { state: State, objective: Objective, player: Player ->
            EnterStructureListener(state, objective, player, structureType)
        }

        fun factory(structureType: org.bukkit.StructureType) = { state: State, objective: Objective, player: Player ->
            EnterStructureListener(state, objective, player, StructureType(structureType))
        }
    }
}
