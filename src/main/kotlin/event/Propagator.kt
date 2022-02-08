package event

import org.bukkit.event.Event

fun interface Propagator<T : Event, R : Event> : Multiplier<T, R> {
    override fun multiply(event: T): List<R> = listOfNotNull(propagate(event))

    fun propagate(event: T): R?
}
