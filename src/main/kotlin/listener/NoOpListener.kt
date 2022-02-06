package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import util.Objective

object NoOpListener : Listener

val noop = { _: State, _: Objective, _: Player -> NoOpListener }
