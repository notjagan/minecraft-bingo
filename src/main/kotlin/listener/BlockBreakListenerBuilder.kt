package listener

import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import util.matches

class BlockBreakListenerBuilder(
    private val block: Material
) : ObjectiveListenerBuilder<BlockBreakEvent> {
    override fun createFactory(): ObjectiveListenerFactory<BlockBreakEvent> =
        ObjectiveListenerFactory { event, player ->
            event.block.type == block && event.player matches player
        }
}
