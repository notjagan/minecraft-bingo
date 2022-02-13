package listener

import org.bukkit.event.player.PlayerLevelChangeEvent
import util.matches

class LevelChangeListenerBuilder(
    private val level: Int
) : ObjectiveListenerBuilder<PlayerLevelChangeEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerLevelChangeEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && event.newLevel >= level
        }
}
