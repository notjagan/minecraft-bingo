package listener

import org.bukkit.Material
import org.bukkit.event.player.PlayerItemHeldEvent
import util.UniqueCount
import util.matches

class MultiItemInInventoryListenerBuilder(
    private val materials: Array<Material>,
    private val count: UniqueCount = UniqueCount.All
) : ObjectiveListenerBuilder<PlayerItemHeldEvent> {
    constructor(materials: Array<Material>, count: Int) : this(materials, UniqueCount.Num(count))

    override fun createFactory(): ObjectiveListenerFactory<PlayerItemHeldEvent> =
        ObjectiveListenerFactory { event, player ->
            val inventory = event.player.inventory
            event.player matches player && when (count) {
                is UniqueCount.Num -> count <= materials.count { inventory.contains(it) }
                is UniqueCount.All -> materials.all { inventory.contains(it) }
            }
        }
}
