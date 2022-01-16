package game

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import util.Objective

class InsufficientObjectivesException : Exception()

class Board(private val state: State) {
    private val listeners = HashSet<Listener>()
    private lateinit var objectives: Array<Array<Objective>>

    fun setObjectives(objectives: Array<Array<Objective>>) {
        this.objectives = objectives
    }

    fun addListeners(player: Player) {
        for (objective in objectives.flatten()) {
            val listener = objective.addListener(state, player)
            listeners.add(listener)
        }
    }

    fun clearListeners() {
        for (listener in listeners) {
            HandlerList.unregisterAll(listener)
        }
    }
}

fun generateRandomBoard(state: State): Board {
    val boardSize = state.settings.boardSize
    val objectiveChoices = Objective.values()
    if (objectiveChoices.size < boardSize * boardSize)
        throw InsufficientObjectivesException()
    objectiveChoices.shuffle()
    val objectives = Array(boardSize) { i ->
        Array(boardSize) { j ->
            objectiveChoices[i * boardSize + j]
        }
    }
    val board = Board(state)
    board.setObjectives(objectives)
    return board
}
