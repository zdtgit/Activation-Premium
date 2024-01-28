package com.activation.premium.listeners.player

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandSendEvent

class PlayerCommandSendListener: Listener {
    @EventHandler
    fun on(event: PlayerCommandSendEvent) {
        val commands = event.commands

        commands.clear()
        commands.add("visit")
        commands.add("home")
    }
}