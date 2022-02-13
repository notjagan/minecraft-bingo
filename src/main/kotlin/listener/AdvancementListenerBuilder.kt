package listener

import org.bukkit.event.player.PlayerAdvancementDoneEvent
import util.matches


class AdvancementListenerBuilder(
    private val advancementName: String
) : ObjectiveListenerBuilder<PlayerAdvancementDoneEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerAdvancementDoneEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && event.advancement.key.key == advancementName
        }
}
