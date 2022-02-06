package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityBreedEvent
import util.Objective
import util.matches

class BreedListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val entityType: EntityType
) : ObjectiveListener<EntityBreedEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: EntityBreedEvent) = event.entity.type == entityType && event.breeder matches player

    companion object {
        fun factory(entityType: EntityType) = { state: State, objective: Objective, player: Player ->
            BreedListener(state, objective, player, entityType)
        }
    }
}
