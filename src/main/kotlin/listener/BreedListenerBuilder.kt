package listener

import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityBreedEvent
import util.matches

class BreedListenerBuilder(
    private val entityType: EntityType
) : ObjectiveListenerBuilder<EntityBreedEvent> {
    override fun createFactory(): ObjectiveListenerFactory<EntityBreedEvent> =
        ObjectiveListenerFactory { event, player ->
            event.entity.type == entityType && event.breeder matches player
        }
}
