package bingosync

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.http4k.core.Body
import org.http4k.core.Response
import org.http4k.format.Jackson.auto

data class Square(
    val name: String,
    @JsonDeserialize(using = ColorsDeserializer::class)
    val colors: HashSet<CellColor>,
    @JsonDeserialize(using = SlotDeserializer::class)
    val slot: Int
) {
    private class ColorsDeserializer : JsonDeserializer<HashSet<CellColor>>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): HashSet<CellColor> {
            val colors = HashSet<CellColor>()
            val colorsString = parser.valueAsString
            if (colorsString == "blank") {
                return colors
            }
            for (colorString in colorsString.split(" "))
                colors.add(CellColor.fromString(colorString)!!)
            return colors
        }
    }

    private class SlotDeserializer : JsonDeserializer<Int>() {
        override fun deserialize(parser: JsonParser, context: DeserializationContext): Int {
            val slotString = parser.valueAsString
            val match = Regex("""slot(\d{1,2})""").find(slotString)!!
            val (slotNumber) = match.destructured
            return slotNumber.toInt() - 1
        }
    }
}

object Squares {
    private val bodyLens = Body.auto<List<Square>>().toLens()
    fun fromResponse(response: Response) = bodyLens(response)
}
