package bingosync

import com.fasterxml.jackson.annotation.*
import org.http4k.client.Java8HttpClient
import org.http4k.client.WebsocketClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.format.Jackson.auto
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import util.Constants

class BingosyncClient(private val roomJoinParameters: RoomJoinParameters) {
    private val baseUri = Uri.of(Constants.BINGOSYNC_ADDRESS)
    private val socketUri = Uri.of(Constants.BINGOSYNC_SOCKET_ADDRESS)
    private var socketParameters: SocketParameters
    private val handler = ClientFilters.Cookies().then(Java8HttpClient())
    private val websocketClient: Websocket

    data class SocketParameters(
        @JsonProperty("socket_key")
        val socketKey: String
    ) {
        companion object {
            private val bodyLens = Body.auto<SocketParameters>().toLens()
            private val messageLens = WsMessage.auto<SocketParameters>().toLens()
            fun fromResponse(response: Response) = bodyLens(response)
        }

        fun toWsMessage() = messageLens(this)
    }

    init {
        socketParameters = getNewSocketKey()
        websocketClient = WebsocketClient.nonBlocking(socketUri.extend(Uri.of("broadcast"))) {
            it.send(socketParameters.toWsMessage())
            updateState()
        }
        websocketClient.onMessage { wsMessage ->
            val message = Message.fromWsMessage(wsMessage)
            println(message)
        }
    }

    private fun getNewSocketKey(): SocketParameters {
        val postRequest = Request(
            Method.POST,
            baseUri.extend(Uri.of("api/join-room"))
        ).body(roomJoinParameters)
        val postResponse = handler(postRequest)
        val redirectPath = postResponse.headers.first {
            it.first == "Location"
        }.second!!
        val getRequest = Request(
            Method.GET,
            baseUri.extend(Uri.of(redirectPath))
        )
        val getResponse = handler(getRequest)
        return SocketParameters.fromResponse(getResponse)
    }

    private fun updateState() {
        val request = Request(
            Method.GET,
            baseUri.extend(Uri.of("room/${roomJoinParameters.roomCode}/board"))
        )
        val response = handler(request)
        val squares = Squares.fromResponse(response)
    }
}
