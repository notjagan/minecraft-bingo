package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.loot.LootTables
import org.bukkit.loot.Lootable
import util.Objective
import util.matches

class LootableListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val lootTable: LootTables
) : ObjectiveListener<InventoryOpenEvent>(state, objective, player) {
    override fun isObjectiveComplete(event: InventoryOpenEvent): Boolean {
        val inventory = event.inventory
        return event.player matches player && inventory is Lootable && lootTable.key == inventory.lootTable?.key
    }

    companion object {
        fun factory(lootTable: LootTables) = { state: State, objective: Objective, player: Player ->
            LootableListener(state, objective, player, lootTable)
        }
    }
}
