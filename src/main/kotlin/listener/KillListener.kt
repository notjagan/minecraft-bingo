package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent
import util.Objective
import util.matches

class KillListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val entityType: EntityType
) : ObjectiveListener<EntityDeathEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: EntityDeathEvent) = event.entity.type == entityType && event.entity.killer matches player

    companion object {
        fun factory(entityType: EntityType) = { state: State, objective: Objective, player: Player ->
            KillListener(state, objective, player, entityType)
        }
    }
}
