package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.inventory.BrewEvent
import org.bukkit.potion.PotionType
import util.Objective
import util.matches
import util.contains

class BrewListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val potionType: PotionType
) : ObjectiveListener<BrewEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: BrewEvent): Boolean =
        event.contents.viewers.any { it matches player } && potionType in event.results

    companion object {
        fun factory(potionType: PotionType) = { state: State, objective: Objective, player: Player ->
            BrewListener(state, objective, player, potionType)
        }
    }
}
