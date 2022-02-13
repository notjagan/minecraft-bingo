package event

import org.bukkit.event.Event
import org.bukkit.plugin.Plugin
import java.lang.reflect.ParameterizedType

sealed class TripleEmitter<T : Event, S : Event, U : Event, V : Event>(
    private val plugin: Plugin,
    private val first: Multiplier<T, V>,
    private val second: Multiplier<S, V>,
    private val third: Multiplier<U, V>
) : Emitter<V> {
    private val firstClass by lazy {
        @Suppress("UNCHECKED_CAST")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
    }
    private val secondClass by lazy {
        @Suppress("UNCHECKED_CAST")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<S>
    }
    private val thirdClass by lazy {
        @Suppress("UNCHECKED_CAST")
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[2] as Class<U>
    }

    override fun registerEvents() {
        first.registerEvent(plugin, firstClass)
        second.registerEvent(plugin, secondClass)
        third.registerEvent(plugin, thirdClass)
    }
}
