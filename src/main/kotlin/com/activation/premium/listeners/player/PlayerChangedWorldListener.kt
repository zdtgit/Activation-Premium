package com.activation.premium.listeners.player

import com.activation.premium.extensions.string.mini
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import java.util.*

class PlayerChangedWorldListener: Listener {
    @EventHandler
    fun on(event: PlayerChangedWorldEvent) {
        val player = event.player
        val uuid = player.uniqueId.toString()
        val world = player.world
        val from = event.from

        if (from.name != uuid && from.name != "world") {
            val offline = Bukkit.getOfflinePlayer(UUID.fromString(from.name))

            player.sendMessage("<gold>${offline.name ?: "Unknown"}<yellow>님의 월드에서 퇴장하셨습니다.".mini)

            if (offline.isOnline && offline.player != null) {
                offline.player!!.sendMessage("<gold>${player.name}<yellow>님이 월드에서 퇴장하셨습니다.".mini)
            }
        }

        if (uuid == world.name) {
            player.gameMode = GameMode.CREATIVE
        } else if (world.name != "world") {
            player.gameMode = GameMode.SPECTATOR

            val owner = Bukkit.getOfflinePlayer(UUID.fromString(world.name))

            player.sendMessage("<bold>${owner.name ?: "Unknown"}<yellow>님의 월드에 접속하셨습니다.".mini)

            if (owner.isOnline && owner.player != null) {
                owner.player!!.sendMessage("<gold>${player.name}<yellow>님이 월드에 접속하셨습니다.".mini)
            }
        }
    }
}