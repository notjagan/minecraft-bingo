package listener

import org.bukkit.Material
import org.bukkit.event.player.PlayerItemHeldEvent
import util.matches

class ItemInInventoryListenerBuilder(
    private val material: Material,
    private val amount: Int
) : ObjectiveListenerBuilder<PlayerItemHeldEvent> {
    constructor(material: Material) : this(material, 1)

    override fun createFactory(): ObjectiveListenerFactory<PlayerItemHeldEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && event.player.inventory.contains(material, amount)
        }
}
