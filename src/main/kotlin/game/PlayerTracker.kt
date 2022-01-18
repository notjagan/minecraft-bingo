package game

import bingosync.CellColor
import util.Objective
import java.util.EnumSet
import kotlin.collections.HashMap

class ColorInUseException() : Exception()

class PlayerTracker(private val objectiveMap: HashMap<String, PlayerData>) {
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
    }

    fun isComplete(playerName: String, objective: Objective) =
        objectiveMap[playerName]?.objectives?.contains(objective) == true

    fun clearObjectives() = objectiveMap.clear()

    fun getPlayerColor(playerName: String) = objectiveMap[playerName]!!.color
}

fun createEmptyTracker(): PlayerTracker {
    return PlayerTracker(HashMap())
}
