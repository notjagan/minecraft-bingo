package listener

import game.State
import org.bukkit.StructureType
import org.bukkit.block.Biome
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerMoveEvent
import util.Objective
import util.contains
import util.matches

class EnterStructureListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val structureType: StructureType,
    private val biomes: Array<Biome>?
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onEnterStructure(event: PlayerMoveEvent) {
        if (event.player matches player && biomes?.any { player in it } != false && player in structureType)
            updateObjectiveStatus()
    }

    companion object {
        fun factory(structureType: StructureType, biomes: Array<Biome>?) =
            { state: State, objective: Objective, player: Player ->
                EnterStructureListener(state, objective, player, structureType, biomes)
            }

        fun factory(structureType: StructureType, biome: Biome) = factory(structureType, arrayOf(biome))

        fun factory(structureType: StructureType) = factory(structureType, null)
    }
}
