package listener

import event.PlayerBrewEvent
import org.bukkit.potion.PotionType
import util.matches

class BrewListenerBuilder(
    private val potionType: PotionType
) : ObjectiveListenerBuilder<PlayerBrewEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerBrewEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && potionType in event.potions
        }
}
