package listener

import org.bukkit.event.entity.PlayerDeathEvent
import util.matches

class PlayerDeathListenerBuilder : ObjectiveListenerBuilder<PlayerDeathEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerDeathEvent> =
        ObjectiveListenerFactory { event, player ->
            event.entity matches player
        }
}
