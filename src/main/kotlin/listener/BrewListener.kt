package listener

import event.PlayerBrewEvent
import game.State
import org.bukkit.entity.Player
import org.bukkit.potion.PotionType
import util.Objective
import util.matches

class BrewListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val potionType: PotionType
) : ObjectiveListener<PlayerBrewEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: PlayerBrewEvent): Boolean =
        event.player matches player && potionType in event.potions

    companion object {
        fun factory(potionType: PotionType) = { state: State, objective: Objective, player: Player ->
            BrewListener(state, objective, player, potionType)
        }
    }
}
