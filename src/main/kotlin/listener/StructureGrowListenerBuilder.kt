package listener

import org.bukkit.TreeType
import org.bukkit.event.world.StructureGrowEvent
import util.matches

class StructureGrowListenerBuilder(
    private val treeTypes: Array<TreeType>
) : ObjectiveListenerBuilder<StructureGrowEvent> {
    constructor(treeType: TreeType) : this(arrayOf(treeType))

    override fun createFactory(): ObjectiveListenerFactory<StructureGrowEvent> =
        ObjectiveListenerFactory { event, player ->
            event.species in treeTypes && event.player matches player
        }
}

