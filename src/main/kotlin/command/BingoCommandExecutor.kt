package command

import bingosync.CellColor
import game.*
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
                } catch (e: InsufficientObjectivesException) {
                    sender.sendMessage("${ChatColor.RED}Insufficient unique objectives to generate board.")
                }
            }
            "join" -> {
                if (sender !is Player)
                    sender.sendMessage("${ChatColor.RED}Invalid player.")
                else if (game == null)
                    sender.sendMessage("${ChatColor.RED}No game in progress.")
                else when (args.size) {
                    1 ->
                        if (game!!.addPlayer(sender)) {
                            val color = game!!.state.tracker.getColorForPlayer(sender.name)
                            Bukkit.getLogger().info(
                                "Added player ${sender.name} to current game with color ${color.name}."
                            )
                            sender.sendMessage("Added with color ${color.name}.")
                        } else
                            sender.sendMessage("${ChatColor.RED}Already in game.")
                    2 -> {
                        val colorString = args[1].lowercase()
                        try {
                            val color = CellColor.valueOf(colorString)
                            if (game!!.addPlayer(sender, color)) {
                                Bukkit.getLogger().info(
                                    "Added player ${sender.name} to current game with color ${color.name}."
                                )
                                sender.sendMessage("Added with color ${color.name}.")
                            } else
                                sender.sendMessage("${ChatColor.RED}Already in game.")
                        } catch (e: IllegalArgumentException) {
                            sender.sendMessage("${ChatColor.RED}Already in game.")
                        } catch (e: ColorInUseException) {
                            sender.sendMessage("${ChatColor.RED}Color $colorString already in use.")
                        }
                    }
                    else -> return false
                }
            }
            "sync" -> {
                val roomCode = args[1]
                val password = args[2]
                game = joinBingosyncGame(plugin, roomCode, password)
            }
            else -> return false
        }
        return true
    }
}
