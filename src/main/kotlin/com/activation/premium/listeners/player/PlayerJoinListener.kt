package com.activation.premium.listeners.player

import com.activation.premium.extensions.string.mini
import com.activation.premium.managers.WorldManager
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener: Listener {
    @EventHandler
    fun on(event: PlayerJoinEvent) {
        val player = event.player
        val uuid = player.uniqueId.toString()
        val world = WorldManager.getWorld(uuid)

        if (world == null) {
            player.kick("<red>월드 로드 실패".mini)
            return
        }

        val location = Location(world, 0.5, 0.0, 0.5, 0f, 0f)

        location.chunk.load()

        player.teleport(location)

        event.joinMessage(null)
    }
}