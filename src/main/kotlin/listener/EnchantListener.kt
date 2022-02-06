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
) : ObjectiveListener<EnchantItemEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: EnchantItemEvent) = event.item.type == item && event.enchanter matches player

    companion object {
        fun factory(item: Material) = { state: State, objective: Objective, player: Player ->
            EnchantListener(state, objective, player, item)
        }
    }
}
