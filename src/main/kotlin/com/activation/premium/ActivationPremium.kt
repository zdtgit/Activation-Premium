package com.activation.premium

import com.activation.premium.managers.CommandManager
import com.activation.premium.managers.ListenerManager
import com.activation.premium.managers.TaskManager
import org.bukkit.plugin.java.JavaPlugin

class ActivationPremium: JavaPlugin() {
    override fun onEnable() {
        CommandManager.init()
        ListenerManager.init()
        TaskManager.init()
    }

    companion object {
        val instance
            get() = getPlugin(ActivationPremium::class.java)
    }
}