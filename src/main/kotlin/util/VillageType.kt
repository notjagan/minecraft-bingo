package util

sealed class VillageType(
    val biome: String
) : StructureType(org.bukkit.StructureType.VILLAGE) {
    object DesertVillage : VillageType("desert")
    object PlainsVillage : VillageType("plain")
    object SavannaVillage : VillageType("savanna")
    object SnowyVillage : VillageType("snowy")
    object TaigaVillage : VillageType("taiga")
}
