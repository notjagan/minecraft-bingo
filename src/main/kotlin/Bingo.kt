import command.BingoCommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class Bingo : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        getCommand("bingo")?.setExecutor(BingoCommandExecutor(this))
    }
}
