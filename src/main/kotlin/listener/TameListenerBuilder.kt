package listener

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityTameEvent
import util.matches

class TameListenerBuilder(
    private val entityType: EntityType
) : ObjectiveListenerBuilder<EntityTameEvent> {
    override fun createFactory(): ObjectiveListenerFactory<EntityTameEvent> =
        ObjectiveListenerFactory { event, player ->
            val owner = event.owner
            event.entity.type == entityType && owner is Player && owner matches player
        }
}
