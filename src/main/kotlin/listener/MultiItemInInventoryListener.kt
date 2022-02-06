package listener

import game.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemHeldEvent
import util.Objective
import util.UniqueCount
import util.matches

class MultiItemInInventoryListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val materials: Array<Material>,
    private val count: UniqueCount
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onItemHeldEvent(event: PlayerItemHeldEvent) {
        if (event.player matches player) {
            val inventory = event.player.inventory
            if (when (count) {
                    is UniqueCount.Num -> count <= materials.count(inventory::contains)
                    is UniqueCount.All -> materials.all(inventory::contains)
                }
            )
                updateObjectiveStatus()
        }
    }

    companion object {
        fun factory(material: Array<Material>, count: UniqueCount = UniqueCount.All) =
            { state: State, objective: Objective, player: Player ->
                MultiItemInInventoryListener(state, objective, player, material, count)
            }

        fun factory(material: Array<Material>, count: Int) = { state: State, objective: Objective, player: Player ->
            MultiItemInInventoryListener(state, objective, player, material, UniqueCount.Num(count))
        }
    }
}
