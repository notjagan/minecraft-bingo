package listener

import game.State
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.Listener
import util.Objective
import util.registerListener

fun interface ObjectiveListenerFactory<T : Event> {
    fun isObjectiveComplete(event: T, player: Player): Boolean

    fun registerEvent(rawClass: Class<out Event>, state: State, objective: Objective, player: Player): Listener {
        val listener = ObjectiveListener<T> { event ->
            val playerName = player.name
            if (isObjectiveComplete(event, player) && !state.tracker.isComplete(playerName, objective)) {
                state.tracker.markComplete(playerName, objective)
                Bukkit.getLogger().info("Player $playerName has complete objective ${objective.name}")
            }
        }
        @Suppress("UNCHECKED_CAST")
        state.plugin.registerListener(rawClass as Class<T>, listener)
        return listener
    }
}
