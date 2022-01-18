package game

import bingosync.CellColor
import util.Objective
import java.util.EnumSet
import kotlin.collections.HashMap

class ColorInUseException() : Exception()

class PlayerTracker(private val objectiveMap: HashMap<String, PlayerData>) {
    var goalUpdateHandler: GoalUpdateHandler? = null

    data class PlayerData(val objectives: EnumSet<Objective>, val color: CellColor)

    fun trackPlayer(playerName: String, color: CellColor) {
        if (objectiveMap.values.any { it.color == color }) {
            throw ColorInUseException()
        }
        objectiveMap[playerName] = PlayerData(EnumSet.noneOf(Objective::class.java), color)
    }

    fun trackPlayer(playerName: String) {
        val usedColors = objectiveMap.values.map(PlayerData::color)
        val color = CellColor.values().first { !usedColors.contains(it) }
        trackPlayer(playerName, color)
    }

    fun isTracking(playerName: String) = objectiveMap.contains(playerName)

    fun markComplete(playerName: String, objective: Objective) {
        if (!isTracking(playerName))
            trackPlayer(playerName)
        objectiveMap[playerName]?.objectives?.add(objective)
        goalUpdateHandler?.handleGoalUpdate(playerName, objective, true)
    }

    fun markNotComplete(playerName: String, objective: Objective) {
        if (!isTracking(playerName))
            trackPlayer(playerName)
        objectiveMap[playerName]?.objectives?.remove(objective)
        goalUpdateHandler?.handleGoalUpdate(playerName, objective, false)
    }

    fun isComplete(playerName: String, objective: Objective) =
        objectiveMap[playerName]?.objectives?.contains(objective) == true

    fun clearObjectives() = objectiveMap.clear()

    fun getColorForPlayer(playerName: String) = objectiveMap[playerName]!!.color

    fun getPlayerForColor(color: CellColor) = objectiveMap.filter { it.value.color == color }.keys.first()
}

fun createEmptyTracker(): PlayerTracker {
    return PlayerTracker(HashMap())
}
