package com.activation.premium.managers

import com.activation.premium.ActivationPremium
import com.activation.premium.tasks.TabTask

object TaskManager {
    private val plugin = ActivationPremium.instance

    fun init() {
        TabTask().runTaskTimer(plugin, 0L, 20L)
    }
}