import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class WEvent(private val m: White) : Listener {

    @EventHandler
    fun onConnect(e: PlayerLoginEvent) {
        val p = e.player
        if (p == null) return
        if (!m.getStorage()?.isWhitelisting()!!!!) return
        if (m.getStorage()?.isWhitelisted(p.name) == true) return
        val msg = m.getStorage()?.getNoWhitelistMsg() ?: ""
        e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, msg)
    }
}
