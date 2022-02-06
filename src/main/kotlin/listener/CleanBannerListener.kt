package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.CauldronLevelChangeEvent
import util.Objective
import util.matches

class CleanBannerListener(
    state: State,
    objective: Objective,
    private val player: Player
) : ObjectiveListener<CauldronLevelChangeEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: CauldronLevelChangeEvent) = event.reason == CauldronLevelChangeEvent.ChangeReason.BANNER_WASH && event.entity matches player
}
