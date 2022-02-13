package event

import org.bukkit.entity.Player
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.inventory.AnvilInventory
import org.bukkit.plugin.Plugin
import util.Enchantments
import util.get
import util.set

@Suppress("unused")
class PlayerUpdateEnchantmentEmitter(plugin: Plugin) :
    TripleEmitter<EnchantItemEvent, PrepareAnvilEvent, InventoryClickEvent, PlayerUpdateEnchantmentEvent>(
        plugin,
        Propagator { event ->
            val enchantments = event.item.itemMeta?.enchants ?: default
            PlayerUpdateEnchantmentEvent(event.enchanter, enchantments)
        },
        Propagator { event ->
            val stack = event.result
            if (stack != null) {
                val block = event.viewers.first().getTargetBlock(null, 7)
                block[key] = serialize(stack.itemMeta?.enchants ?: default)
            }
            null
        },
        Propagator { event ->
            if (
                event.inventory is AnvilInventory &&
                event.view.getSlotType(event.rawSlot) == InventoryType.SlotType.RESULT
            ) {
                val block = event.viewers.first().getTargetBlock(null, 7)
                val metadata = block[key] ?: defaultMetadata
                PlayerUpdateEnchantmentEvent((event.whoClicked as Player), deserialize(metadata))
            } else
                null
        }
    ) {
    companion object : MetadataProperty<Enchantments>("resultEnchantments", mutableMapOf())
}
