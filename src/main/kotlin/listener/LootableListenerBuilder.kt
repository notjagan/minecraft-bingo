package listener

import org.bukkit.block.Chest
import org.bukkit.event.inventory.InventoryOpenEvent
import util.StructureType
import util.contains
import util.matches

class LootableListenerBuilder(
    private val structureType: StructureType
) : ObjectiveListenerBuilder<InventoryOpenEvent> {
    constructor(structureType: org.bukkit.StructureType) : this(StructureType(structureType))

    override fun createFactory(): ObjectiveListenerFactory<InventoryOpenEvent> =
        ObjectiveListenerFactory { event, player ->
            val holder = event.inventory.holder
            event.player matches player && holder is Chest && holder.block in structureType
        }
}
