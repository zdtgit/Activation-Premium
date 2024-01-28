package com.activation.premium.listeners.entity

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent

class EntityExplodeListener: Listener {
    @EventHandler
    fun on(event: EntityExplodeEvent) {
        event.isCancelled = true
    }
}