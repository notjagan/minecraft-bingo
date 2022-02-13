package listener

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.ProjectileLaunchEvent
import util.matches

class ProjectileLaunchListenerBuilder(
    private val projectile: EntityType
) : ObjectiveListenerBuilder<ProjectileLaunchEvent> {
    override fun createFactory(): ObjectiveListenerFactory<ProjectileLaunchEvent> =
        ObjectiveListenerFactory { event, player ->
            val shooter = event.entity.shooter
            event.entity.type == projectile && shooter is Player && shooter matches player
        }
}
