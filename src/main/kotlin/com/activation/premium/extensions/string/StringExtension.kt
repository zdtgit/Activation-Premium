package com.activation.premium.extensions.string

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

val String.mini: Component
    get() = MiniMessage.miniMessage().deserialize(this)