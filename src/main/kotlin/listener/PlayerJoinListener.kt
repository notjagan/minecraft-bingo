package listener

import game.Game
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val game: Game) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) = game.addPlayer(event.player)
}
