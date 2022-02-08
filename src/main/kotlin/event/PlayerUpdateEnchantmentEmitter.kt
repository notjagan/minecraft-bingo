package event

import org.bukkit.entity.Player
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.inventory.AnvilInventory
import org.bukkit.inventory.BlockInventoryHolder
import org.bukkit.plugin.Plugin
import util.Enchantments
import util.get
import util.set

@Suppress("unused")
class PlayerUpdateEnchantmentEmitter(plugin: Plugin) : Emitter<PlayerUpdateEnchantmentEvent>(
    plugin,
    Propagator { event: EnchantItemEvent ->
        val enchantments = event.item.itemMeta?.enchants ?: default
        PlayerUpdateEnchantmentEvent(event.enchanter, enchantments)
    },
    Propagator { event: PrepareAnvilEvent ->
        val stack = event.result
        if (stack != null) {
            val block = (event.inventory.holder as BlockInventoryHolder).block
            block[key] = serialize(stack.itemMeta?.enchants ?: default)
        }
        null
    },
    Propagator { event: InventoryClickEvent ->
        if (
            event.inventory is AnvilInventory &&
            event.view.getSlotType(event.rawSlot) == InventoryType.SlotType.RESULT
        ) {
            val block = (event.inventory.holder as BlockInventoryHolder).block
            val metadata = block[key] ?: defaultMetadata
            PlayerUpdateEnchantmentEvent((event.whoClicked as Player), deserialize(metadata))
        } else
            null
    }
) {
    companion object : MetadataProperty<Enchantments>("result", mutableMapOf())
}
