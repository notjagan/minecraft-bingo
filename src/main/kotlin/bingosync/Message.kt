package bingosync

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ErrorMessage::class, name = "error"),
    JsonSubTypes.Type(value = GoalMessage::class, name = "goal"),
    JsonSubTypes.Type(value = NewCardMessage::class, name = "new-card"),
    JsonSubTypes.Type(
        value = MiscellaneousMessage::class,
        names = [
            "chat",
            "color",
            "revealed",
            "connection"
        ]
    )
)
abstract class Message

data class ErrorMessage(
    @JsonProperty("error")
    val error: String
) : Message()

data class GoalMessage(
    @JsonProperty("square")
    private val square: Square
) : Message() {
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
                val match = Regex("slot(\\d{1,2})").find(slotString)!!
                val (slotNumber) = match.destructured
                return slotNumber.toInt()
            }
        }
    }
}

object NewCardMessage : Message()

data class MiscellaneousMessage(
    @JsonProperty("type")
    private val type: String
) : Message()
