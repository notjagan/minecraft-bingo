package listener

import org.bukkit.event.Event
import org.bukkit.event.Listener
import java.lang.reflect.ParameterizedType

fun interface ObjectiveListenerBuilder<T : Event> : Listener, ListenerBuilder<T> {
    fun createFactory(): ObjectiveListenerFactory<T>

    @Suppress("UNCHECKED_CAST")
    fun eventClass(): Class<T> =
        (javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments[0] as Class<T>
}
