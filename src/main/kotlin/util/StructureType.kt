package util

import org.bukkit.StructureType

open class StructureType(val type: StructureType) {
    val name by type::name
}
