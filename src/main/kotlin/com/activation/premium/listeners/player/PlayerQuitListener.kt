package com.activation.premium.listeners.player

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener: Listener {
    @EventHandler
    fun on(event: PlayerQuitEvent) {
        event.quitMessage(null)
    }
}