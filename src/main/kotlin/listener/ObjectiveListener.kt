package listener

import game.State
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import util.Objective

abstract class ObjectiveListener(
    private val state: State,
    private val objective: Objective,
    private val player: Player
) : Listener {
    internal fun updateObjectiveStatus() {
        val playerName = player.name
        if (!state.tracker.isComplete(playerName, objective)) {
            state.tracker.markComplete(playerName, objective)
            Bukkit.getLogger().info("Player $playerName has complete objective ${objective.name}")
        }
    }
}
