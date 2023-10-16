import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import utils.Utility

class WCommand(private val m: EasyWhiteList) : CommandExecutor {
    private val prefix = "&c&lWhiteList"

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (!sender.hasPermission("advw.admin")) return true
        remanage(sender, args)
        return true
    }

    private fun remanage(snd: CommandSender, args: Array<String>) {
        if (args.isEmpty()) {
            Utility.sendMsg(snd, "&c&lWhitelist")
            Utility.sendMsg(snd, "&e> &7/advw &aadd &f<name>")
            Utility.sendMsg(snd, "&e> &7/advw &cremove &f<name>")
            Utility.sendMsg(snd, "&e> &7/advw &flist")
            Utility.sendMsg(snd, "&e> &7/advw &a&lon")
            Utility.sendMsg(snd, "&e> &7/advw &c&loff")
            Utility.sendMsg(snd, "&e> &7/advw &creload")
            return
        }

        when (args[0].toLowerCase()) {
            "add" -> {
                if (args.size < 2) {
                    Utility.sendMsg(snd, "&7Please input a name!")
                    return
                }
                var name = args[1]
                if (args.size > 2) {
                    name = "$name ${args[2]}"
                }
                if (args.size > 3) {
                    name = "$name ${args[2]} ${args[3]}"
                }
                m.getStorage()?.addWhitelist(name)
                Utility.sendMsg(snd, "$prefix Whitelisted '&a$name&7'")
            }
            "remove" -> {
                if (args.size < 2) {
                    Utility.sendMsg(snd, "&7Please input a name!")
                    return
                }
                var name = args[1]
                if (args.size > 2) {
                    name = "$name ${args[2]}"
                }
                if (args.size > 3) {
                    name = "$name ${args[2]} ${args[3]}"
                }
                m.getStorage()?.removeWhitelist(name)
                Utility.sendMsg(snd, "$prefix Whitelist removed for &c$name")
            }
            "on" -> {
                m.getStorage()?.setWhitelist(true)
                Utility.sendMsg(snd, "$prefix &fWhitelist is now &a&lON&f!")
            }
            "off" -> {
                m.getStorage()?.setWhitelist(false)
                Utility.sendMsg(snd, "$prefix &8Whitelist is &c&lOFF!&8")
            }
            "list" -> {
                val names = m.getStorage()?.getWhiteLists()?.joinToString("&e&l, &7")
                Utility.sendMsg(snd, "&a&lWhitelisted: &7$names")
            }
            "reload" -> {
                m.getStorage()?.reload()
            }
            else -> {
                Utility.sendMsg(snd, "&a&lWhitelist &7>")
                Utility.sendMsg(snd, "&e> &7/easywl &aadd &f<name>")
                Utility.sendMsg(snd, "&e> &7/easywl &cremove &f<name>")
                Utility.sendMsg(snd, "&e> &7/easywl &flist")
                Utility.sendMsg(snd, "&e> &7/easywl &a&lon")
                Utility.sendMsg(snd, "&e> &7/easywl &c&loff")
                Utility.sendMsg(snd, "&e> &7/easywl &creload")
            }
        }
    }
}
