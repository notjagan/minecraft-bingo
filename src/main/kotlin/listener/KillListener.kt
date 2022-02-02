package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent
import util.Objective

class KillListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val entityType: EntityType
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onKill(event: EntityDeathEvent) {
        if (event.entity.type == entityType && event.entity.killer?.uniqueId == player.uniqueId)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(entityType: EntityType) = { state: State, objective: Objective, player: Player ->
            KillListener(state, objective, player, entityType)
        }
    }
}
