import command.BingoCommandExecutor
import event.Emitter
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class Bingo : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        getCommand("bingo")?.setExecutor(BingoCommandExecutor(this))
        for (subclass in Emitter::class.sealedSubclasses) {
            val instance = subclass.java.getDeclaredConstructor(Plugin::class.java).newInstance(this)
            instance.registerEvents()
        }
    }
}
