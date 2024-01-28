package com.activation.premium.listeners.player

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener: Listener {
    @EventHandler
    fun on(event: PlayerDeathEvent) {
        event.isCancelled = true
    }
}