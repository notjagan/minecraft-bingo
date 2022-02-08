package event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import util.Enchantments

class PlayerUpdateEnchantmentEvent(
    player: Player,
    val enchantments: Enchantments
) : PlayerEvent(player) {
    private val handlers = HandlerList()
    override fun getHandlers() = handlers
}
