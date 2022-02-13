package listener

import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDeathEvent
import util.matches

class KillListenerBuilder(
    private val entityType: EntityType
) : ObjectiveListenerBuilder<EntityDeathEvent> {
    override fun createFactory(): ObjectiveListenerFactory<EntityDeathEvent> =
        ObjectiveListenerFactory { event, player ->
            event.entity.type == entityType && event.entity.killer matches player
        }
}
