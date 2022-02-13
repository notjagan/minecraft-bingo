import command.BingoCommandExecutor
import event.Emitter
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import util.allSealedSubclasses

class Bingo : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        getCommand("bingo")?.setExecutor(BingoCommandExecutor(this))
        for (subclass in Emitter::class.allSealedSubclasses()) {
            val instance = subclass.java.getDeclaredConstructor(Plugin::class.java).newInstance(this)
            instance.registerEvents()
        }
    }
}
