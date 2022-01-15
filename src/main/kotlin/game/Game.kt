package game

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import util.ObjectiveTracker

class Game(private val state: State, private val board: Board) {
    fun addPlayer(player: Player) {
        val playerName = player.name
        if (!state.tracker.isTracking(playerName)) {
            board.addListeners(player)
            state.tracker.trackPlayer(playerName)
        }
    }
}

fun createGame(plugin: Plugin): Game {
    val state = State(plugin, ObjectiveTracker(), Settings())
    val board = generateRandomBoard(state)
    return Game(state, board)
}
