package util

import net.minecraft.core.BaseBlockPosition
import net.minecraft.world.level.levelgen.feature.StructureGenerator
import net.minecraft.world.level.levelgen.feature.structures.WorldGenFeatureDefinedStructurePoolSingle
import net.minecraft.world.level.levelgen.structure.WorldGenFeaturePillagerOutpostPoolPiece
import org.bukkit.block.Biome
import org.bukkit.craftbukkit.v1_18_R1.CraftChunk
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionType

infix fun Entity?.matches(other: Entity) = this?.uniqueId == other.uniqueId

operator fun Iterable<ItemStack>.contains(potionType: PotionType) = this.any { stack ->
    val meta = stack.itemMeta
    meta is PotionMeta && meta.basePotionData.type == potionType
}

operator fun StructureType.contains(player: Player): Boolean {
    val location = player.location
    val structureLocation = player.world.locateNearestStructure(location, this.type, 200, false) ?: return false
    val chunk = (player.world.getChunkAt(structureLocation) as CraftChunk).handle
    val pieces = chunk.a(StructureGenerator.b[type.name.lowercase()])?.i() ?: return false
    val position = BaseBlockPosition(location.x, location.y, location.z)
    if (pieces.any { it.f().b(position) }) {
        if (this is VillageType) {
            val pattern = Regex("""minecraft:village/([^/]*)""")
            fun String.filterCommon() = if (this == "common") null else this
            val villageBiome = pieces.firstNotNullOfOrNull {
                ((it as? WorldGenFeaturePillagerOutpostPoolPiece)
                    ?.b() as? WorldGenFeatureDefinedStructurePoolSingle)
                    ?.toString()
                    ?.let(pattern::find)
                    ?.destructured
                    ?.component1()
                    ?.filterCommon()
            }
            return villageBiome == biome
        }
        return true
    }
    return false
}

operator fun Biome.contains(player: Player): Boolean = this == player.world.getBiome(player.location)
