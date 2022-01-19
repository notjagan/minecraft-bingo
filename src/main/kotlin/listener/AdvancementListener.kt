package listener

import game.State
import org.bukkit.Material
import org.bukkit.advancement.Advancement
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import util.Objective


class AdvancementListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val advancementName: String
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onAdvancement(event: PlayerAdvancementDoneEvent) {
        if (event.player.name == player.name && event.advancement.key.key == advancementName)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(advancementName: String) = { state: State, objective: Objective, player: Player ->
            AdvancementListener(state, objective, player, advancementName)
        }
    }
}
