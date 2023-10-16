import org.bukkit.configuration.file.FileConfiguration
import utils.Utility

class WStorage(private val m: White) {
    private var whitelists = mutableListOf<String>()
    private var WhitelistEnabled = false
    private var nowhitelistmsg = ""

    init {
        reload()
    }

    fun reload() {
        m.reloadConfig()
        val config: FileConfiguration = m.config
        whitelists = ArrayList(config.getStringList("whitelisted"))
        WhitelistEnabled = config.getBoolean("whitelist")
        nowhitelistmsg = Utility.TransColor(config.getString("no_whitelisted"))
        Utility.sendConsole("&e&lEasyWhitelist > &7Config reloaded.")
    }

    fun saveWhitelists() {
        val config: FileConfiguration = m.config
        config.set("whitelisted", whitelists)
        config.set("whitelist", isWhitelisting())
        m.saveConfig()
    }

    fun isWhitelisted(name: String): Boolean {
        return whitelists.contains(name.toLowerCase())
    }

    fun addWhitelist(name: String) {
        if (whitelists.contains(name.toLowerCase())) return
        whitelists.add(name.toLowerCase())
        saveWhitelists()
    }

    fun removeWhitelist(name: String) {
        if (!whitelists.contains(name.toLowerCase())) return
        whitelists.remove(name.toLowerCase())
        saveWhitelists()
    }

    fun setWhitelist(onoff: Boolean) {
        WhitelistEnabled = onoff
        saveWhitelists()
    }

    fun getWhiteLists(): List<String> {
        return whitelists
    }

    fun isWhitelisting(): Boolean {
        return WhitelistEnabled
    }

    fun getNoWhitelistMsg(): String {
        return nowhitelistmsg
    }
}