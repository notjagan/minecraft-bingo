package listener

import event.PlayerUpdateEnchantmentEvent
import org.bukkit.enchantments.Enchantment
import util.matches

class PlayerUpdateEnchantmentListenerBuilder(
    private val enchantment: Enchantment,
    private val level: Int
) : ObjectiveListenerBuilder<PlayerUpdateEnchantmentEvent> {
    override fun createFactory(): ObjectiveListenerFactory<PlayerUpdateEnchantmentEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && (event.enchantments[enchantment] ?: 0) >= level
        }
}
