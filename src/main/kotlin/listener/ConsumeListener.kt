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
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onEat(event: PlayerItemConsumeEvent) {
        if (event.item.type == item && event.player matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(item: Material) = { state: State, objective: Objective, player: Player ->
            ConsumeListener(state, objective, player, item)
        }
    }
}
