package listener

import event.PlayerUpdateEnchantmentEvent
import game.State
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import util.Objective
import util.matches

class PlayerUpdateEnchantmentListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val enchantment: Enchantment,
    private val level: Int
) : ObjectiveListener<PlayerUpdateEnchantmentEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: PlayerUpdateEnchantmentEvent) =
        event.player matches player && (event.enchantments[enchantment] ?: 0) >= level

    companion object {
        fun factory(enchantment: Enchantment, level: Int) = { state: State, objective: Objective, player: Player ->
            PlayerUpdateEnchantmentListener(state, objective, player, enchantment, level)
        }
    }
}
