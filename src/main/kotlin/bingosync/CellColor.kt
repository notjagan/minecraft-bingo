package bingosync

import com.fasterxml.jackson.annotation.JsonValue

enum class CellColor(
    @JsonValue
    val colorString: String
) {
    ORANGE("orange"),
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple"),
    NAVY("navy"),
    TEAL("teal"),
    BROWN("brown"),
    PINK("pink"),
    YELLOW("yellow");

    companion object {
        private val map = CellColor.values().associateBy(CellColor::colorString)

        fun fromString(colorString: String) = map[colorString]
    }
}
