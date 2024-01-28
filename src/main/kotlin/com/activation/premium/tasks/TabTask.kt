package com.activation.premium.tasks

import com.activation.premium.extensions.string.mini
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class TabTask : BukkitRunnable() {
    override fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            val world = player.world
            val name = world.name
            var playerName: String

            val owner = Bukkit.getOfflinePlayer(UUID.fromString(name))

            playerName = owner.name ?: ""

            val header = "<gradient:aqua:blue><bold>ACTIVATION 개인 월드".mini.appendNewline()
            val footer = Component.newline().append("<gold>${playerName}<yellow>님의 월드에 있습니다.".mini)

            player.sendPlayerListHeaderAndFooter(header, footer)
        }
    }
}