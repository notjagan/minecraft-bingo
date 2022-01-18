package bingosync

import com.fasterxml.jackson.annotation.*
import game.Game
import game.GoalUpdateHandler
import org.http4k.client.Java8HttpClient
import org.http4k.client.WebsocketClient
import org.http4k.core.*
import org.http4k.filter.ClientFilters
import org.http4k.format.Jackson.auto
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import util.Constants
import util.Objective

class BingosyncClient(private val roomJoinParameters: RoomJoinParameters, private val game: Game) : GoalUpdateHandler {
    private val baseUri = Uri.of(Constants.BingosyncAddress)
    private val socketUri = Uri.of(Constants.BingosyncSocketAddress)
    private var socketParameters: SocketParameters
    private val handler = ClientFilters.Cookies().then(Java8HttpClient())
    private val websocketClient: Websocket
    private lateinit var squares: List<Square>

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
            squares = getSquares()
            updateBoard()
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

    private fun getSquares(): List<Square> {
        val request = Request(
            Method.GET,
            baseUri.extend(Uri.of("room/${roomJoinParameters.roomCode}/board"))
        )
        val response = handler(request)
        return Squares.fromResponse(response)
    }

    private fun updateBoard() {
        val map = squares
            .associateBy(Square::slot)
            .mapValues {
                it.value.name.toObjective()
            }
        val boardSize = game.state.settings.boardSize
        val objectives = Array(boardSize) { i ->
            Array(boardSize) { j ->
                map.getValue(boardSize * i + j)
            }
        }
        game.board.setObjectives(objectives)
        for (square in squares) {
            val playerNames = square.colors.map(game.state.tracker::getPlayerForColor)
            for (playerName in playerNames) {
                game.state.tracker.markComplete(playerName, square.name.toObjective())
            }
        }
    }

    override fun handleGoalUpdate(playerName: String, objective: Objective, isComplete: Boolean) {
        val slot = game.board.getIndex(objective)
        val goalUpdateParameters = GoalUpdateParameters(
            roomJoinParameters.roomCode,
            game.state.tracker.getColorForPlayer(playerName),
            slot,
            isComplete
        )
        val request = Request(
            Method.PUT,
            baseUri.extend(Uri.of("api/select"))
        ).body(goalUpdateParameters)
        handler(request)
    }
}
