package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityTameEvent
import util.Objective

class TameListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val entityType: EntityType
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onTame(event: EntityTameEvent) {
        if (event.entity.type == entityType && event.owner.uniqueId == player.uniqueId)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(entityType: EntityType) = { state: State, objective: Objective, player: Player ->
            TameListener(state, objective, player, entityType)
        }
    }
}
