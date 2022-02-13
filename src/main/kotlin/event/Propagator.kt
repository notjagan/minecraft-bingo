package event

import org.bukkit.event.Event

fun interface Propagator<T : Event, S : Event> : Multiplier<T, S> {
    override fun multiply(event: T): List<S> = listOfNotNull(propagate(event))

    fun propagate(event: T): S?
}
