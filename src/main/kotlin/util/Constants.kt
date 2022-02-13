package util

import org.bukkit.Material

object Constants {
    object Bundles {
        val ConcreteBlocks = arrayOf(
            Material.BLACK_CONCRETE,
            Material.BLUE_CONCRETE,
            Material.BROWN_CONCRETE,
            Material.CYAN_CONCRETE,
            Material.GRAY_CONCRETE,
            Material.GREEN_CONCRETE,
            Material.LIGHT_BLUE_CONCRETE,
            Material.LIGHT_GRAY_CONCRETE,
            Material.LIME_CONCRETE,
            Material.MAGENTA_CONCRETE,
            Material.ORANGE_CONCRETE,
            Material.PINK_CONCRETE,
            Material.PURPLE_CONCRETE,
            Material.RED_CONCRETE,
            Material.WHITE_CONCRETE,
            Material.YELLOW_CONCRETE,
        )
        val GlazedTerracottaBlocks = arrayOf(
            Material.BLACK_GLAZED_TERRACOTTA,
            Material.BLUE_GLAZED_TERRACOTTA,
            Material.BROWN_GLAZED_TERRACOTTA,
            Material.CYAN_GLAZED_TERRACOTTA,
            Material.GRAY_GLAZED_TERRACOTTA,
            Material.GREEN_GLAZED_TERRACOTTA,
            Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
            Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
            Material.LIME_GLAZED_TERRACOTTA,
            Material.MAGENTA_GLAZED_TERRACOTTA,
            Material.ORANGE_GLAZED_TERRACOTTA,
            Material.PINK_GLAZED_TERRACOTTA,
            Material.PURPLE_GLAZED_TERRACOTTA,
            Material.RED_GLAZED_TERRACOTTA,
            Material.WHITE_GLAZED_TERRACOTTA,
            Material.YELLOW_GLAZED_TERRACOTTA,
        )
        val Dyes = arrayOf(
            Material.BLACK_DYE,
            Material.BLUE_DYE,
            Material.BROWN_DYE,
            Material.CYAN_DYE,
            Material.GRAY_DYE,
            Material.GREEN_DYE,
            Material.LIGHT_BLUE_DYE,
            Material.LIGHT_GRAY_DYE,
            Material.LIME_DYE,
            Material.MAGENTA_DYE,
            Material.ORANGE_DYE,
            Material.PINK_DYE,
            Material.PURPLE_DYE,
            Material.RED_DYE,
            Material.WHITE_DYE,
            Material.YELLOW_DYE,
        )
        val Stews = arrayOf(
            Material.MUSHROOM_STEW,
            Material.RABBIT_STEW,
            Material.SUSPICIOUS_STEW,
        )
        val Doors = arrayOf(
            Material.ACACIA_DOOR,
            Material.BIRCH_DOOR,
            Material.CRIMSON_DOOR,
            Material.DARK_OAK_DOOR,
            Material.IRON_DOOR,
            Material.JUNGLE_DOOR,
            Material.OAK_DOOR,
            Material.SPRUCE_DOOR,
            Material.WARPED_DOOR,
            Material.ACACIA_TRAPDOOR,
            Material.BIRCH_TRAPDOOR,
            Material.CRIMSON_TRAPDOOR,
            Material.DARK_OAK_TRAPDOOR,
            Material.IRON_TRAPDOOR,
            Material.JUNGLE_TRAPDOOR,
            Material.OAK_TRAPDOOR,
            Material.SPRUCE_TRAPDOOR,
            Material.WARPED_TRAPDOOR,
        )
        val DiamondTools = arrayOf(
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_SWORD,
        )
        val IronTools = arrayOf(
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_PICKAXE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
        )
        val StoneTools = arrayOf(
            Material.STONE_AXE,
            Material.STONE_HOE,
            Material.STONE_PICKAXE,
            Material.STONE_SHOVEL,
            Material.STONE_SWORD,
        )
        val WoodTools = arrayOf(
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_SWORD,
        )
        val MobHeads = arrayOf(
            Material.CREEPER_HEAD,
            Material.DRAGON_HEAD,
            Material.ZOMBIE_HEAD,
        )
        val Flowers = arrayOf(
            Material.ALLIUM,
            Material.AZURE_BLUET,
            Material.BLUE_ORCHID,
            Material.CORNFLOWER,
            Material.DANDELION,
            Material.LILAC,
            Material.LILY_OF_THE_VALLEY,
            Material.ORANGE_TULIP,
            Material.OXEYE_DAISY,
            Material.PEONY,
            Material.PINK_TULIP,
            Material.POPPY,
            Material.RED_TULIP,
            Material.ROSE_BUSH,
            Material.SUNFLOWER,
            Material.WHITE_TULIP,
            Material.WITHER_ROSE,
        )
    }

    const val BingosyncAddress = "https://bingosync.com"
    const val BingosyncSocketAddress = "wss://sockets.bingosync.com"
    const val GeneratorPath = "/bingosync/bingosync-app/generators/minecraft_1_17_1_generator.js"
}
