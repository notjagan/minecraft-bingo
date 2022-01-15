package game

import org.bukkit.entity.Player
import util.Objective

class InsufficientObjectivesException : Exception()

class Board(private val state: State, private val objectives: Array<Array<Objective>>) {
    fun addListeners(player: Player) {
        for (objective in objectives.flatten())
            objective.addListener(state, player)
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
    return Board(state, objectives)
}
