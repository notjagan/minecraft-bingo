package bingosync

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.http4k.format.Jackson.auto
import org.http4k.websocket.WsMessage

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
abstract class Message {
    companion object {
        private val wsMessageLens = WsMessage.auto<Message>().toLens()
        fun fromWsMessage(wsMessage: WsMessage) = wsMessageLens(wsMessage)
    }
}

data class ErrorMessage(
    @JsonProperty("error")
    val error: String
) : Message()

data class GoalMessage(
    @JsonProperty("square")
    private val square: Square
) : Message()

object NewCardMessage : Message()

data class MiscellaneousMessage(
    @JsonProperty("type")
    private val type: String
) : Message()
