package event

import org.bukkit.event.Event
import org.bukkit.plugin.Plugin

sealed class Emitter<T : Event>(protected val plugin: Plugin, private vararg val propagators: Propagator<*, T>) {
    fun registerEvents() = propagators.forEach { it.registerEvent(plugin) }
}
