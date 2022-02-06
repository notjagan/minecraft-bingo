package listener

import game.State
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import util.Objective

abstract class ObjectiveListener<T : Event>(
    private val state: State,
    private val objective: Objective,
    private val player: Player
) : Listener {
    abstract fun isObjectiveComplete(event: T): Boolean

    @EventHandler
    fun handle(event: T) {
        val playerName = player.name
        if (isObjectiveComplete(event) && !state.tracker.isComplete(playerName, objective)) {
            state.tracker.markComplete(playerName, objective)
            Bukkit.getLogger().info("Player $playerName has complete objective ${objective.name}")
        }
    }
}
