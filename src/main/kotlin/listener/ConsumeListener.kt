package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import util.Objective
import util.matches

class ConsumeListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val item: Material
) : ObjectiveListener<PlayerItemConsumeEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: PlayerItemConsumeEvent) = event.item.type == item && event.player matches player

    companion object {
        fun factory(item: Material) = { state: State, objective: Objective, player: Player ->
            ConsumeListener(state, objective, player, item)
        }
    }
}
