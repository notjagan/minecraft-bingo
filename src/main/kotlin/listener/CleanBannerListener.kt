package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.CauldronLevelChangeEvent
import util.Objective

class CleanBannerListener(
    state: State,
    objective: Objective,
    private val player: Player
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onCauldronLevelChange(event: CauldronLevelChangeEvent) {
        if (event.reason == CauldronLevelChangeEvent.ChangeReason.BANNER_WASH && event.entity?.uniqueId == player.uniqueId)
            updateObjectiveStatus()
    }
}
