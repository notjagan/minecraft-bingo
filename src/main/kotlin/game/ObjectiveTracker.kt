package game

import util.Objective
import java.util.EnumSet
import kotlin.collections.HashMap

@JvmInline
value class ObjectiveTracker(private val objectiveMap: HashMap<String, EnumSet<Objective>>) {
    constructor() : this(HashMap<String, EnumSet<Objective>>())

    fun trackPlayer(playerName: String) {
        objectiveMap[playerName] = EnumSet.noneOf(Objective::class.java)
    }

    fun isTracking(playerName: String) = objectiveMap.contains(playerName)

    fun markComplete(playerName: String, objective: Objective) {
        if (!isTracking(playerName))
            trackPlayer(playerName)
        objectiveMap[playerName]?.add(objective)
    }

    fun isComplete(playerName: String, objective: Objective) = objectiveMap[playerName]?.contains(objective) == true

    fun clearObjectives() = objectiveMap.clear()
}
