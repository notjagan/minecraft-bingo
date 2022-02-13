package listener

import org.bukkit.event.Event
import org.bukkit.event.Listener

fun interface ObjectiveListener<T : Event> : Listener {
    @Suppress("UNUSED")
    fun onEvent(event: T)
}
