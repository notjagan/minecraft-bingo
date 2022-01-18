package command

import game.Game
import game.InsufficientObjectivesException
import game.createRandomGame
import listener.PlayerJoinListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class BingoCommandExecutor(private val plugin: Plugin) : CommandExecutor {
    private var game: Game? = null

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty())
            return false
        when (args[0]) {
            "start" -> {
                if (args.size > 1)
                    return false
                try {
                    game = createRandomGame(plugin)
                    Bukkit.getServer().pluginManager.registerEvents(PlayerJoinListener(game!!), plugin)
                } catch (e: InsufficientObjectivesException) {
                    sender.sendMessage("${ChatColor.RED}Insufficient unique objectives to generate board.")
                }
            }
            "join" -> {
                if (args.size > 1)
                    return false
                else if (sender !is Player)
                    sender.sendMessage("${ChatColor.RED}Invalid player.")
                else if (game == null)
                    sender.sendMessage("${ChatColor.RED}No game in progress.")
                else if (game!!.addPlayer(sender))
                    Bukkit.getLogger().info("Added player ${sender.name} to current game.")
                else
                    sender.sendMessage("${ChatColor.RED}Already in game.")
            }
            else -> return false
        }
        return true
    }
}
