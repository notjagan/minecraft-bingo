package game

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class Game(val state: State, private val board: Board) {
    fun addPlayer(player: Player): Boolean {
        val playerName = player.name
        if (!state.tracker.isTracking(playerName)) {
            board.addListeners(player)
            state.tracker.trackPlayer(playerName)
            return true
        }
        return false
    }
}

fun createGame(plugin: Plugin): Game {
    val state = State(plugin, ObjectiveTracker(), Settings())
    val board = generateRandomBoard(state)
    return Game(state, board)
}
