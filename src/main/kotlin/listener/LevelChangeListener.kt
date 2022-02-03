package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerLevelChangeEvent
import util.Objective
import util.matches

class LevelChangeListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val level: Int
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onLevelChange(event: PlayerLevelChangeEvent) {
        if (event.player matches player && event.newLevel >= level)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(level: Int) = { state: State, objective: Objective, player: Player ->
            LevelChangeListener(state, objective, player, level)
        }
    }
}
