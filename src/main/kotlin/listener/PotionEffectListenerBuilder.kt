package listener

import org.bukkit.event.entity.EntityPotionEffectEvent
import org.bukkit.potion.PotionEffectType
import util.matches

class PotionEffectListenerBuilder(
    private val effectType: PotionEffectType
) : ObjectiveListenerBuilder<EntityPotionEffectEvent> {
    override fun createFactory(): ObjectiveListenerFactory<EntityPotionEffectEvent> =
        ObjectiveListenerFactory { event, player ->
            event.newEffect?.type == effectType && event.entity matches player
        }
}
