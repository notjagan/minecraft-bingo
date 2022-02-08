package event

import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

fun interface Multiplier<T : Event, R : Event> {
    fun multiply(event: T): List<R>

    fun registerEvent(plugin: Plugin) = plugin.server.pluginManager.registerEvents(object : Listener {
        @EventHandler
        fun onEvent(event: T) {
            for (result in multiply(event))
                plugin.server.pluginManager.callEvent(result)
        }
    }, plugin)
}
