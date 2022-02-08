package util

import Bingo
import net.minecraft.core.BaseBlockPosition
import net.minecraft.world.level.levelgen.feature.StructureGenerator
import net.minecraft.world.level.levelgen.feature.structures.WorldGenFeatureDefinedStructurePoolSingle
import net.minecraft.world.level.levelgen.structure.WorldGenFeaturePillagerOutpostPoolPiece
import org.bukkit.block.Biome
import org.bukkit.craftbukkit.v1_18_R1.CraftChunk
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.metadata.Metadatable
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionType
import kotlin.reflect.typeOf

typealias BrewHistory = MutableSet<PotionType>

infix fun Entity?.matches(other: Entity) = this?.uniqueId == other.uniqueId

operator fun StructureType.contains(player: Player): Boolean {
    val location = player.location
    val structureLocation = player.world.locateNearestStructure(location, this.type, 200, false) ?: return false
    val chunk = (player.world.getChunkAt(structureLocation) as CraftChunk).handle
    val pieces = chunk.a(StructureGenerator.b[name.lowercase()])?.i() ?: return false
    val position = BaseBlockPosition(location.x, location.y, location.z)
    if (pieces.any { it.f().b(position) }) {
        if (this is VillageType) {
            val pattern = Regex("""minecraft:village/([^/]*)""")
            val villageBiome = pieces.firstNotNullOfOrNull { piece ->
                ((piece as? WorldGenFeaturePillagerOutpostPoolPiece)
                    ?.b() as? WorldGenFeatureDefinedStructurePoolSingle)
                    ?.toString()
                    ?.let { pattern.find(it) }
                    ?.destructured
                    ?.component1()
                    ?.takeUnless { it == "common" }
            }
            return villageBiome == biome
        }
        return true
    }
    return false
}

operator fun Biome.contains(player: Player): Boolean = this == player.world.getBiome(player.location)

inline operator fun <reified T> Metadatable.get(key: String): T? {
    val metadata = getMetadata(key).firstOrNull { it.owningPlugin is Bingo } ?: return null
    return when (typeOf<T>().classifier) {
        Int::class -> metadata.asInt()
        Float::class -> metadata.asFloat()
        Long::class -> metadata.asLong()
        Short::class -> metadata.asShort()
        Byte::class -> metadata.asByte()
        Boolean::class -> metadata.asBoolean()
        String::class -> metadata.asString()
        else -> metadata.value()
    } as T
}

operator fun Metadatable.set(key: String, value: Any) {
    val metadata = FixedMetadataValue(JavaPlugin.getPlugin(Bingo::class.java), value)
    setMetadata(key, metadata)
}
