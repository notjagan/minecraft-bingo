package game

import bingosync.CellColor
import util.Objective
import java.util.EnumSet
import kotlin.collections.HashMap

class ColorInUseException() : Exception()

class PlayerTracker(private val objectiveMap: MutableMap<String, PlayerData>) {
    var updateHandler: GameUpdateHandler? = null

    data class PlayerData(val objectives: EnumSet<Objective>, val color: CellColor)

    fun trackPlayer(playerName: String, color: CellColor) {
        if (objectiveMap.values.any { it.color == color }) {
            throw ColorInUseException()
        }
        objectiveMap[playerName] = PlayerData(EnumSet.noneOf(Objective::class.java), color)
        updateHandler?.handleNewPlayer(playerName)
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
        updateHandler?.handleGoalUpdate(playerName, objective, true)
    }

    fun markNotComplete(playerName: String, objective: Objective) {
        if (!isTracking(playerName))
            trackPlayer(playerName)
        objectiveMap[playerName]?.objectives?.remove(objective)
        updateHandler?.handleGoalUpdate(playerName, objective, false)
    }

    fun setPlayers(objective: Objective, playerNames: List<String>) {
        clearObjective(objective)
        for (playerName in playerNames)
            objectiveMap[playerName]?.objectives?.add(objective)
    }

    fun isComplete(playerName: String, objective: Objective) =
        objectiveMap[playerName]?.objectives?.contains(objective) == true

    private fun clearObjective(objective: Objective) {
        for (playerData in objectiveMap.values)
            playerData.objectives.remove(objective)
    }

    fun clearObjectives() {
        for (playerData in objectiveMap.values)
            playerData.objectives.clear()
    }

    fun getColorForPlayer(playerName: String) = objectiveMap[playerName]!!.color

    fun getPlayerForColor(color: CellColor) = objectiveMap.filter { it.value.color == color }.keys.firstOrNull()
}

fun createEmptyTracker(): PlayerTracker {
    return PlayerTracker(HashMap())
}
