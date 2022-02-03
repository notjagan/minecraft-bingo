package util

import net.minecraft.core.BaseBlockPosition
import net.minecraft.world.level.levelgen.feature.StructureGenerator
import org.bukkit.StructureType
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
    val structureLocation = player.world.locateNearestStructure(location, this, 200, false) ?: return false
    val chunk = (player.world.getChunkAt(structureLocation) as CraftChunk).handle
    val pieces = chunk.a(StructureGenerator.b[this.name.lowercase()])?.i() ?: return false
    val position = BaseBlockPosition(location.x, location.y, location.z)
    return pieces.any { it.f().b(position) }
}

operator fun Biome.contains(player: Player): Boolean = this == player.world.getBiome(player.location)
