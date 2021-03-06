package util

import game.State
import listener.ObjectiveListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player

enum class Objective(val listenerFactory: () -> (State, Player) -> ObjectiveListener) {
    ;

    fun addListener(state: State, player: Player) {
        val listener = listenerFactory()(state, player)
        Bukkit.getServer().pluginManager.registerEvents(listener, state.plugin)
    }
}
