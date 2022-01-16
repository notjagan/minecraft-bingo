package bingosync

import com.fasterxml.jackson.annotation.*
import org.http4k.client.Java8HttpClient
import org.http4k.client.WebsocketClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.format.Jackson.auto
import org.http4k.format.Json
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import util.Constants

class BingosyncClient(private val roomJoinParameters: RequestParameters) {
    private val handler = ClientFilters.Cookies().then(Java8HttpClient())
    private val baseUri = Uri.of(Constants.BINGOSYNC_ADDRESS)
    private val socketUri = Uri.of(Constants.BINGOSYNC_SOCKET_ADDRESS)
    private val requestLens = Body.auto<RequestParameters>().toLens()
    private val socketBodylens = Body.auto<SocketParameters>().toLens()
    private val socketMessageLens = WsMessage.auto<SocketParameters>().toLens()
    private val messageLens = WsMessage.auto<Message>().toLens()
    private val websocketClient: Websocket
    private var socketParameters: SocketParameters

    data class SocketParameters(
        @JsonProperty("socket_key")
        val socketKey: String
    )

    init {
        socketParameters = getNewSocketKey()
        websocketClient = WebsocketClient.nonBlocking(socketUri.extend(Uri.of("broadcast"))) {
            it.send(socketMessageLens(socketParameters))
        }
        websocketClient.onMessage { wsMessage ->
            val message = messageLens(wsMessage)
            println(message)
        }
    }

    private fun getNewSocketKey(): SocketParameters {
        val postRequest = requestLens(
            roomJoinParameters,
            Request(
                Method.POST,
                baseUri.extend(Uri.of("api/join-room"))
            )
        )
        val postResponse = handler(postRequest)
        val redirectPath = postResponse.headers.first {
            it.first == "Location"
        }.second
        val getRequest = Request(
            Method.GET,
            baseUri.extend(Uri.of(redirectPath ?: "/"))
        )
        val getResponse = handler(getRequest)
        return socketBodylens(getResponse)
    }
}
