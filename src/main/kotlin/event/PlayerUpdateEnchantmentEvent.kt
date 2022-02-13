package event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import util.Enchantments

class PlayerUpdateEnchantmentEvent(
    player: Player,
    val enchantments: Enchantments
) : PlayerEvent(player) {
    override fun getHandlers() = Companion.handlers

    companion object {
        private val handlers = HandlerList()

        @Suppress("unused")
        @JvmStatic
        fun getHandlerList() = handlers
    }
}
