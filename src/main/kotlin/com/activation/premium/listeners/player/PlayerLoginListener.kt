package com.activation.premium.listeners.player

import com.activation.premium.extensions.string.mini
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLoginListener: Listener {
    @EventHandler
    fun on(event: PlayerLoginEvent) {
        val player = event.player

        if (!player.hasPermission("group.premium")) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "<red>프리미엄 전용 서버".mini)
        }
    }
}