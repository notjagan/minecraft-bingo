package bingosync

import org.apache.commons.text.StringEscapeUtils
import util.Constants
import util.Objective
import java.util.*

object BingosyncObjective {
    // initialized with manual override values
    // (handles e.g. typos in the bingosync source)
    val nameToObjective: HashMap<String, Objective> = hashMapOf(
        "\"It It a Bird?\" advancement" to Objective.IsItABirdAdvancement
    )

    init {
        val contents = BingosyncObjective::class.java.getResource(Constants.GeneratorPath)!!.readText()
        val objectivePattern = Regex("""^\s*"(.*)",?$""", RegexOption.MULTILINE)
        val identifierPattern = Regex("""^[a-zA-Z]\w*$""")
        for (rawString in objectivePattern.findAll(contents).map { it.groupValues[1] }) {
            val javaString = StringEscapeUtils.unescapeJava(rawString)
            if (javaString in nameToObjective)
                continue
            val words = rawString
                .filter { char ->
                    char.isLetterOrDigit() || char.isWhitespace()
                }
                .split(" ")
                .map { word ->
                    word.replaceFirstChar { letter ->
                        letter.uppercase()
                    }
                }
                .toMutableList()
            if (words.last() != "Advancement") {
                words.removeAll { word ->
                    word == "A" || word == "An" || word == "The"
                }
            }
            if (words.first().all { it.isDigit() })
                Collections.rotate(words, -1)
            val objectiveName = words.joinToString("")
            assert(identifierPattern.matches(objectiveName))
            val objective = Objective.values().find { it.name == objectiveName } ?: Objective.Unknown
            nameToObjective[javaString] = objective
        }
    }
}

fun String.toObjective() = BingosyncObjective.nameToObjective.getValue(this)
