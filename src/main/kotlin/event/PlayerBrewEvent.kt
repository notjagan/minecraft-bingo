package event

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent
import org.bukkit.potion.PotionType

class PlayerBrewEvent(
    player: Player,
    val potions: Iterable<PotionType>
) : PlayerEvent(player) {
    private val handlers = HandlerList()
    override fun getHandlers() = handlers
}
