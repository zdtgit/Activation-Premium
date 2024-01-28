package com.activation.premium.managers

import com.activation.premium.ActivationPremium
import com.activation.premium.listeners.entity.EntityExplodeListener
import com.activation.premium.listeners.player.*
import org.bukkit.event.Listener

object ListenerManager {
    private val plugin = ActivationPremium.instance

    fun init() {
        register(EntityExplodeListener())

        register(PlayerChangedWorldListener())
        register(PlayerCommandSendListener())
        register(PlayerDeathListener())
        register(PlayerJoinListener())
        register(PlayerLoginListener())
        register(PlayerQuitListener())
    }

    private fun register(listener: Listener) {
        plugin.server.pluginManager.registerEvents(listener, plugin)
    }
}