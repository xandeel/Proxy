package utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Effect
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.PlayerInventory
import java.util.ArrayList
import java.util.List

object Utility {
    private const val CENTER_PX = 154

    fun sendMsg(b: CommandSender, msg: String) {
        b.sendMessage(TransColor(msg))
    }

    fun sendConsole(msg: String) {
        Bukkit.getServer().consoleSender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
    }

    fun TransColor(c: String): String {
        return ChatColor.translateAlternateColorCodes('&', c)
    }

    enum class DefaultFontInfo(val character: Char, val length: Int) {
        A('A', 5),
        a('a', 5),
        B('B', 5),
        b('b', 5),
        C('C', 5),
        c('c', 5),
        D('D', 5),
        d('d', 5),
        E('E', 5),
        e('e', 5),
        F('F', 5),
        f('f', 4),
        G('G', 5),
        g('g', 5),
        H('H', 5),
        h('h', 5),
        I('I', 3),
        i('i', 1),
        J('J', 5),
        j('j', 5),
        K('K', 5),
        k('k', 4),
        L('L', 5),
        l('l', 1),
        M('M', 5),
        m('m', 5),
        N('N', 5),
        n('n', 5),
        O('O', 5),
        o('o', 5),
        P('P', 5),
        p('p', 5),
        Q('Q', 5),
        q('q', 5),
        R('R', 5),
        r('r', 5),
        S('S', 5),
        s('s', 5),
        T('T', 5),
        t('t', 4),
        U('U', 5),
        u('u', 5),
        V('V', 5),
        v('v', 5),
        W('W', 5),
        w('w', 5),
        X('X', 5),
        x('x', 5),
        Y('Y', 5),
        y('y', 5),
        Z('Z', 5),
        z('z', 5),
        NUM_1('1', 5),
        NUM_2('2', 5),
        NUM_3('3', 5),
        NUM_4('4', 5),
        NUM_5('5', 5),
        NUM_6('6', 5),
        NUM_7('7', 5),
        NUM_8('8', 5),
        NUM_9('9', 5),
        NUM_0('0', 5),
        EXCLAMATION_POINT('!', 1),
        AT_SYMBOL('@', 6),
        NUM_SIGN('#', 5),
        DOLLAR_SIGN('$', 5),
        PERCENT('%', 5),
        UP_ARROW('^', 5),
        AMPERSAND('&', 5),
        ASTERISK('*', 5),
        LEFT_PARENTHESIS('(', 4),
        RIGHT_PERENTHESIS(')', 4),
        MINUS('-', 5),
        UNDERSCORE('_', 5),
        PLUS_SIGN('+', 5),
        EQUALS_SIGN('=', 5),
        LEFT_CURL_BRACE('{', 4),
        RIGHT_CURL_BRACE('}', 4),
        LEFT_BRACKET('[', 3),
        RIGHT_BRACKET(']', 3),
        COLON(':', 1),
        SEMI_COLON(';', 1),
        DOUBLE_QUOTE('"', 3),
        SINGLE_QUOTE('\'', 1),
        LEFT_ARROW('<', 4),
        RIGHT_ARROW('>', 4),
        QUESTION_MARK('?', 5),
        SLASH('/', 5),
        BACK_SLASH('\\', 5),
        LINE('|', 1),
        TILDE('~', 5),
        TICK('`', 2),
        PERIOD('.', 1),
        COMMA(',', 1),
        SPACE(' ', 3),
        DEFAULT('a', 4);

        fun getCharacter(): Char {
            return character
        }

        fun getLength(): Int {
            return length
        }

        fun getBoldLength(): Int {
            return if (this == SPACE) getLength() else length + 1
        }

        companion object {
            fun getDefaultFontInfo(c: Char): DefaultFontInfo {
                for (dFI in values()) {
                    if (dFI.getCharacter() == c) return dFI
                }
                return DEFAULT
            }
        }
    }

    fun TransColor(c: List<String>) {
        val strf = StringBuilder()
        val length = c.size
        var cr = 0
        for (str in c) {
            cr++
            strf.append(str)
            if (cr != length) {
                strf.append(";")
            }
        }


        fun PlaySoundAt(w: World, p: Location, s: Sound, vol: Float, pit: Float) {
            w.playSound(p, s, vol, pit)
        }

        fun PlaySound(p: Player, s: Sound, vol: Float, pit: Float) {
            p.playSound(p.location, s, vol, pit)
        }

        fun near(loc: Entity, radius: Int): ArrayList<Player> {
            val nearby = ArrayList<Player>()
            for (e in loc.getNearbyEntities(radius.toDouble(), radius.toDouble(), radius.toDouble())) {
                if (e is Player) {
                    nearby.add(e)
                }
            }
            return nearby
        }

        fun PlayParticle(world: World, loc: Location, particle: Effect, count: Int) {
            world.playEffect(loc, particle, count)
        }

        fun normalizeTime(seconds: Int): String {
            var sec = seconds
            var min = 0
            var hour = 0
            var day = 0
            while (sec >= 60) {
                min++
                sec -= 60
            }
            while (min >= 60) {
                hour++
                min -= 60
            }
            while (hour >= 24) {
                day++
                hour -= 24
            }
            return when {
                sec == 0 && min == 0 && hour == 0 && day == 0 -> "&a&lZERO!"
                min == 0 && hour == 0 && day == 0 -> "$sec Seconds"
                hour == 0 && day == 0 && min > 0 -> "$min Minutes $sec Seconds"
                day == 0 && hour > 0 -> "$hour Hours $min Minutes $sec Seconds"
                day > 0 -> "$day Days $hour Hours $min Minutes $sec Seconds"
                else -> "&a&lZERO!"
            }
        }

        fun normalizeTime2(seconds: Int): String {
            var sec = seconds
            var min = 0
            var hour = 0
            var day = 0
            while (sec >= 60) {
                min++
                sec -= 60
            }
            while (min >= 60) {
                hour++
                min -= 60
            }
            while (hour >= 24) {
                day++
                hour -= 24
            }
            return when {
                sec == 0 && min == 0 && hour == 0 && day == 0 -> "&a&lZERO!"
                min == 0 && hour == 0 && day == 0 -> "$sec sec"
                hour == 0 && day == 0 && min > 0 -> "$min min $sec sec"
                day == 0 && hour > 0 -> "$hour h $min min $sec sec"
                day > 0 -> "$day day $hour h $min min $sec sec"
                else -> "&a&lZERO!"
            }
        }

        fun isEmpty(inv: Inventory): Boolean {
            for (i in 0 until inv.size) {
                if (inv.getItem(i) == null) {
                    return true
                }
            }
            return false
        }

        fun isEmpty(inv: PlayerInventory): Boolean {
            return isEmpty(inv as Inventory)
        }
    }
}

