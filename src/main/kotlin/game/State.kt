package game

import org.bukkit.plugin.Plugin

data class State(val plugin: Plugin, var tracker: ObjectiveTracker, var settings: Settings)
