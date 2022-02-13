package event

import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import util.registerListener

fun interface Multiplier<T : Event, S : Event> {
    fun multiply(event: T): List<S>

    fun registerEvent(plugin: Plugin, eventClass: Class<T>) = plugin.registerListener(
        eventClass,
        object : Listener {
            @EventHandler
            fun onEvent(event: T) {
                for (result in multiply(event))
                    plugin.server.pluginManager.callEvent(result)
            }
        }
    )
}
