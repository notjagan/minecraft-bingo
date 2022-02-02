package listener

import game.State
import net.minecraft.core.BaseBlockPosition
import net.minecraft.world.level.levelgen.feature.StructureGenerator
import org.bukkit.StructureType
import org.bukkit.block.Biome
import org.bukkit.craftbukkit.v1_18_R1.CraftChunk
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerMoveEvent
import util.Objective

class EnterStructureListener(
    state: State,
    objective: Objective,
    private val player: Player,
    private val structureType: StructureType,
    private val biomes: Array<Biome>?
) : ObjectiveListener(state, objective, player) {
    @EventHandler
    fun onEnterStructure(event: PlayerMoveEvent) {
        if (biomes?.contains(player.world.getBiome(player.location)) == true && player.isInStructure(structureType))
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

fun Player.isInStructure(structureType: StructureType): Boolean {
    val structureLocation = world.locateNearestStructure(
        location,
        structureType,
        200,
        false
    ) ?: return false
    val chunk = (world.getChunkAt(structureLocation.chunk.x, structureLocation.chunk.z) as CraftChunk).handle
    val pieces = chunk.a(StructureGenerator.b[structureType.name.lowercase()])?.i() ?: return false
    val position = BaseBlockPosition(
        location.x,
        location.y,
        location.z
    )
    return pieces.any { it.f().b(position) }
}
