package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import util.Objective
import util.matches

class PlayerDeathListener(
    state: State,
    objective: Objective,
    private val player: Player
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        if (event.entity matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory() = { state: State, objective: Objective, player: Player ->
            PlayerDeathListener(state, objective, player)
        }
    }
}
