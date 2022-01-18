package util

import game.State
import listener.ItemInInventoryListener
import listener.NoOpListener
import listener.ObjectiveListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

enum class Objective(val listenerFactory: (State, Objective, Player) -> ObjectiveListener) {
    Unknown(::NoOpListener);

    fun addListener(state: State, player: Player): ObjectiveListener {
        val listener = listenerFactory(state, this, player)
        Bukkit.getServer().pluginManager.registerEvents(listener, state.plugin)
        return listener
    }
}
