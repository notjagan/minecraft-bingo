package bingosync

import com.fasterxml.jackson.annotation.JsonValue

enum class CellColor(
    @JsonValue
    val colorString: String
) {
    Orange("orange"),
    Red("red"),
    Blue("blue"),
    Green("green"),
    Purple("purple"),
    Navy("navy"),
    Teal("teal"),
    Brown("brown"),
    Pink("pink"),
    Yellow("yellow");

    companion object {
        private val nameMap = values().associateBy { it.colorString }
        fun fromString(colorString: String) = nameMap[colorString]
    }
}
