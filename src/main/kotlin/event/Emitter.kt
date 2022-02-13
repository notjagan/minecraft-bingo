package event

import org.bukkit.event.Event

sealed interface Emitter<T : Event> {
    fun registerEvents()
}
