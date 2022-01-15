package game

import org.bukkit.plugin.Plugin
import util.ObjectiveTracker

data class State(val plugin: Plugin, var tracker: ObjectiveTracker, var settings: Settings)
