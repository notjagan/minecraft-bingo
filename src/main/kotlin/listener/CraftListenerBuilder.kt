package listener

import org.bukkit.Material
import org.bukkit.event.inventory.CraftItemEvent
import util.matches

class CraftListenerBuilder(
    private val material: Material
) : ObjectiveListenerBuilder<CraftItemEvent> {
    override fun createFactory(): ObjectiveListenerFactory<CraftItemEvent> =
        ObjectiveListenerFactory { event, player ->
            event.recipe.result.type == material && event.whoClicked matches player
        }
}
