package bingosync

import com.fasterxml.jackson.annotation.JsonProperty
import org.http4k.client.ApacheClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.format.Jackson.auto
import util.Constants

class BingosyncClient(private val roomParameters: RoomParameters) {
    private val handler = ClientFilters.Cookies().then(ApacheClient())
    private val baseUri = Uri.of(Constants.BINGOSYNC_ADDRESS)
    private val roomLens = Body.auto<RoomParameters>().toLens()
    private val socketLens = Body.auto<SocketParameters>().toLens()

    data class RoomParameters(
        @JsonProperty("room")
        val roomCode: String,
        val nickname: String,
        val password: String,
        @JsonProperty("is_spectator")
        val isSpectator: Boolean = false
    )

    data class SocketParameters(
        @JsonProperty("socket_key")
        val socketKey: String
    )

    fun getNewSocketKey(): SocketParameters {
        val postRequest = roomLens(
            roomParameters,
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
        return socketLens(getResponse)
    }
}
