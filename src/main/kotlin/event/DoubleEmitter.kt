package event

import org.bukkit.event.Event
import org.bukkit.plugin.Plugin
import java.lang.reflect.ParameterizedType

sealed class DoubleEmitter<T : Event, S : Event, U : Event>(
    private val plugin: Plugin,
    private val first: Multiplier<T, U>,
    private val second: Multiplier<S, U>
) : Emitter<U> {
    private val firstClass by lazy {
        @Suppress("UNCHECKED_CAST")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
    }
    private val secondClass by lazy {
        @Suppress("UNCHECKED_CAST")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<S>
    }

    override fun registerEvents() {
        first.registerEvent(plugin, firstClass)
        second.registerEvent(plugin, secondClass)
    }
}
