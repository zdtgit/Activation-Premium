package com.activation.premium.commands

import com.activation.premium.managers.WorldManager
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class HomeCommand: TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val uuid = sender.uniqueId.toString()

            val world = WorldManager.getWorld(uuid)

            if (world == null) {
                sender.sendMessage("월드를 찾지 못 했습니다.")

                return true
            }

            sender.teleport(Location(world, 0.5, 0.0, 0.5, 0f, 0f))
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        return emptyList()
    }
}