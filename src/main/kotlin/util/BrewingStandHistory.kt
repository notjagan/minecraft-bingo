package util

import com.fasterxml.jackson.annotation.JsonValue
import org.bukkit.potion.PotionType

data class BrewingStandHistory(
    @JsonValue
    val potions: HashSet<PotionType>
) : MutableSet<PotionType> by potions
