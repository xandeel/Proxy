import org.bukkit.plugin.java.JavaPlugin
import utils.Utility
import org.bukkit.command.PluginCommand
import java.io.File

class White : JavaPlugin() {
    private var storage: WStorage? = null

    override fun onEnable() {
        instance = this
        val f = File(dataFolder, "config.yml")
        if (!f.exists()) {
            config.options().copyDefaults(true)
            saveConfig()
        }
        getCommand("easywhitelist")?.setExecutor(wCommand = (this))
        server.pluginManager.registerEvents(WEvent(this), this)
        storage = WStorage(this)
        Utility.sendConsole("&eE-Whitelist > &7Loaded!")
    }

    override fun onDisable() {
        storage?.saveWhitelists()
    }

    fun getStorage(): WStorage? {
        return storage
    }

    companion object {
        private var instance: White? = null

        fun getInstance(): White? {
            return instance
        }
    }
}

private fun PluginCommand.setExecutor(wCommand: White) {
    TODO("Not yet implemented")
}
