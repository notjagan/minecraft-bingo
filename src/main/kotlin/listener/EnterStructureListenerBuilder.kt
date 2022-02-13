package listener

import org.bukkit.event.player.PlayerMoveEvent
import util.StructureType
import util.contains
import util.matches

class EnterStructureListenerBuilder(
    private val structureType: StructureType,
) : ObjectiveListenerBuilder<PlayerMoveEvent> {
    constructor(structureType: org.bukkit.StructureType) : this(StructureType(structureType))

    override fun createFactory(): ObjectiveListenerFactory<PlayerMoveEvent> =
        ObjectiveListenerFactory { event, player ->
            event.player matches player && player in structureType
        }
}
