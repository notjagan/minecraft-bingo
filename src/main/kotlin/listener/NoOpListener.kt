package listener

import game.State
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import util.Objective

class NoOpListener(state: State, objective: Objective, player: Player) : ObjectiveListener(state, objective, player)