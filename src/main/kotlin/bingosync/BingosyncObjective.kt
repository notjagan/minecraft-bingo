package bingosync

import org.apache.commons.text.StringEscapeUtils
import util.Constants
import util.Objective
import java.util.*
import kotlin.collections.HashMap

object BingosyncObjective {
    val map: HashMap<String, Objective> = HashMap()

    init {
        val contents = BingosyncObjective::class.java.getResource(Constants.GeneratorPath)!!.readText()
        val objectivePattern = Regex("""^\s*"(.*)",?$""", RegexOption.MULTILINE)
        val identiferPattern = Regex("""^[a-zA-Z]\w*$""")
        for (rawString in objectivePattern.findAll(contents).map { it.groupValues[1] }) {
            val javaString = StringEscapeUtils.unescapeJava(rawString)
            var words = rawString
                .filter { char ->
                    char.isLetterOrDigit() or char.isWhitespace()
                }
                .split(" ")
                .map { word ->
                    word.replaceFirstChar { letter ->
                        letter.uppercase()
                    }
                }
            if (words.last() != "Advancement") {
                words = words.filter { word ->
                    !(word == "A" || word == "An" || word == "The")
                }
            }
            if (words.first().all { it.isDigit() })
                Collections.rotate(words, -1)
            val objectiveName = words.joinToString("")
            assert(identiferPattern.matches(objectiveName))
            val objective = Objective.values().find { it.name == objectiveName } ?: Objective.Unknown
            map[javaString] = objective
        }
    }
}

fun String.toObjective() = BingosyncObjective.map[this]
