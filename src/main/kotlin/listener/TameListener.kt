package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityTameEvent
import util.Objective
import util.matches

class TameListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val entityType: EntityType
) : ObjectiveListener<EntityTameEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: EntityTameEvent): Boolean {
        val owner = event.owner
        return event.entity.type == entityType && owner is Player && owner matches player
    }

    companion object {
        fun factory(entityType: EntityType) = { state: State, objective: Objective, player: Player ->
            TameListener(state, objective, player, entityType)
        }
    }
}
