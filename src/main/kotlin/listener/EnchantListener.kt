package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.enchantment.EnchantItemEvent
import util.Objective
import util.matches

class EnchantListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val item: Material
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onEnchant(event: EnchantItemEvent) {
        if (event.item.type == item && event.enchanter matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(item: Material) = { state: State, objective: Objective, player: Player ->
            EnchantListener(state, objective, player, item)
        }
    }
}
