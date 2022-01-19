package game

import bingosync.BingosyncClient
import bingosync.CellColor
import bingosync.RoomJoinParameters
import listener.PlayerJoinListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
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
    HandlerList.unregisterAll()
    val state = State(plugin, createEmptyTracker(), Settings())
    val board = generateRandomBoard(state)
    val game = Game(state, board)
    Bukkit.getServer().pluginManager.registerEvents(PlayerJoinListener(game), plugin)
    return game
}

fun joinBingosyncGame(plugin: Plugin, roomCode: String, password: String): Game {
    HandlerList.unregisterAll()
    val tracker = createEmptyTracker()
    val state = State(plugin, tracker, Settings(boardSize = 5))
    val board = Board(state)
    val game = Game(state, board)
    val roomJoinParameters = RoomJoinParameters(roomCode, "admin", password)
    val client = BingosyncClient(roomJoinParameters, game)
    tracker.updateHandler = client
    Bukkit.getServer().pluginManager.registerEvents(PlayerJoinListener(game), plugin)
    return game
}
