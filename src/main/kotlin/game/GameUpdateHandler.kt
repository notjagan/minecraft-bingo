package game

import bingosync.CellColor
import util.Objective

interface GameUpdateHandler {
    fun handleGoalUpdate(playerName: String, objective: Objective, isComplete: Boolean)
    fun handleNewPlayer(playerName: String)
}
