package game

import util.Objective

fun interface GoalUpdateHandler {
    fun handleGoalUpdate(playerName: String, objective: Objective, isComplete: Boolean)
}
