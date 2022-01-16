package bingosync

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestParameters(
    @JsonProperty("room")
    val roomCode: String,
    val nickname: String,
    val password: String,
    @JsonProperty("is_spectator")
    val isSpectator: Boolean = false,
)
