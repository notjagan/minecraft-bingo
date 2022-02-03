package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityPotionEffectEvent
import org.bukkit.potion.PotionEffectType
import util.Objective
import util.matches

class PotionEffectListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val effectType: PotionEffectType
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onPotionEffect(event: EntityPotionEffectEvent) {
        if (event.newEffect?.type == effectType && event.entity matches player)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(effectType: PotionEffectType) = { state: State, objective: Objective, player: Player ->
            PotionEffectListener(state, objective, player, effectType)
        }
    }
}
