package event

import org.bukkit.event.Event
import org.bukkit.plugin.Plugin

sealed class Emitter<T : Event>(protected val plugin: Plugin, private vararg val multipliers: Multiplier<*, T>) {
    fun registerEvents() = multipliers.forEach { it.registerEvent(plugin) }
}
