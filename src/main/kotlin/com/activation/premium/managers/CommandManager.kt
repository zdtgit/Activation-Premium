package com.activation.premium.managers

import com.activation.premium.ActivationPremium
import com.activation.premium.commands.HomeCommand
import com.activation.premium.commands.VisitCommand
import org.bukkit.command.TabExecutor

object CommandManager {
    private val plugin = ActivationPremium.instance

    fun init() {
        register("home", HomeCommand())
        register("visit", VisitCommand())
    }

    private fun register(name: String, executor: TabExecutor) {
        val command = plugin.getCommand(name)

        if (command != null) {
            command.setExecutor(executor)
            command.tabCompleter = executor
        }
    }
}