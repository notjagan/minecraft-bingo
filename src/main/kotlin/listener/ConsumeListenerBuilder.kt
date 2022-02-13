package listener

import org.bukkit.Material
import org.bukkit.event.player.PlayerItemConsumeEvent
import util.matches

class ConsumeListenerBuilder(
    private val item: Material
) : ObjectiveListenerBuilder<PlayerItemConsumeEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerItemConsumeEvent> =
        ObjectiveListenerFactory { event, player ->
            event.item.type == item && event.player matches player
        }
}
