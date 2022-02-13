package listener

import org.bukkit.Material
import org.bukkit.event.block.BlockPlaceEvent
import util.matches

class BlockPlaceListenerBuilder(
    private val block: Material
) : ObjectiveListenerBuilder<BlockPlaceEvent> {
    override fun createFactory(): ObjectiveListenerFactory<BlockPlaceEvent> =
        ObjectiveListenerFactory { event, player ->
            event.block.type == block && event.player matches player
        }
}
