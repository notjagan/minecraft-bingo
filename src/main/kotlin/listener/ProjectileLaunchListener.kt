package listener

import game.State
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.ProjectileLaunchEvent
import util.Objective
import util.matches

class ProjectileLaunchListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val projectile: EntityType
) : ObjectiveListener<ProjectileLaunchEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: ProjectileLaunchEvent): Boolean {
        val shooter = event.entity.shooter
        return event.entity.type == projectile && shooter is Player && shooter matches player
    }

    companion object {
        fun factory(projectile: EntityType) = { state: State, objective: Objective, player: Player ->
            ProjectileLaunchListener(state, objective, player, projectile)
        }
    }
}
