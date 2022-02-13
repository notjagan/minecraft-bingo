package listener

import org.bukkit.Material
import org.bukkit.event.enchantment.EnchantItemEvent
import util.matches

class EnchantListenerBuilder(
    private val item: Material
) : ObjectiveListenerBuilder<EnchantItemEvent> {
    override fun createFactory(): ObjectiveListenerFactory<EnchantItemEvent> =
        ObjectiveListenerFactory { event, player ->
            event.item.type == item && event.enchanter matches player
        }
}
