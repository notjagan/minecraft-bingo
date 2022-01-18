package game

import bingosync.BingosyncClient
import bingosync.CellColor
import bingosync.RoomJoinParameters
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class Game(val state: State, val board: Board) {
    fun addPlayer(player: Player): Boolean {
        val playerName = player.name
        if (!state.tracker.isTracking(playerName)) {
            board.addListeners(player)
            state.tracker.trackPlayer(playerName)
            return true
        }
        return false
    }

    fun addPlayer(player: Player, color: CellColor): Boolean {
        val playerName = player.name
        if (!state.tracker.isTracking(playerName)) {
            board.addListeners(player)
            state.tracker.trackPlayer(playerName, color)
            return true
        }
        return false
    }
}

fun createRandomGame(plugin: Plugin): Game {
    val state = State(plugin, createEmptyTracker(), Settings())
    val board = generateRandomBoard(state)
    return Game(state, board)
}

fun joinBingosyncGame(plugin: Plugin, roomCode: String, password: String): Game {
    val tracker = createEmptyTracker()
    val state = State(plugin, tracker, Settings(boardSize = 5))
    val board = Board(state)
    val game = Game(state, board)
    val roomJoinParameters = RoomJoinParameters(roomCode, "admin", password)
    val client = BingosyncClient(roomJoinParameters, game)
    tracker.updateHandler = client
    return game
}
