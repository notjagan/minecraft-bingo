package bingosync

import com.fasterxml.jackson.annotation.JsonProperty
import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.format.Jackson.auto
import org.http4k.lens.BiDiBodyLens

abstract class RoomRequestParameters(
    @JsonProperty("room")
    open val roomCode: String
) {
    abstract fun prepareRequest(request: Request): Request
}

data class RoomJoinParameters(
    override val roomCode: String,
    val nickname: String,
    val password: String,
    @JsonProperty("is_spectator")
    val isSpectator: Boolean = false
) : RoomRequestParameters(roomCode) {
    companion object {
        private val bodyLens = Body.auto<RoomJoinParameters>().toLens()
    }

    override fun prepareRequest(request: Request) = bodyLens(this, request)
}

fun Request.body(roomRequestParameters: RoomRequestParameters) = roomRequestParameters.prepareRequest(this)
