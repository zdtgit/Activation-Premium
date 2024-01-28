package com.activation.premium.commands

import com.activation.premium.ActivationPremium
import com.activation.premium.extensions.string.mini
import com.activation.premium.managers.WorldManager
import io.github.monun.invfx.InvFX
import io.github.monun.invfx.openFrame
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class VisitCommand: TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isEmpty()) {
                val players = Bukkit.getOfflinePlayers().toList()

                val frame = InvFX.frame(6, "다른 사람의 월드".mini) {
                    list(1, 1, 7, 3, true, { players }) {
                        transform { offline ->
                            ItemStack(Material.PLAYER_HEAD).apply {
                                itemMeta = (itemMeta as SkullMeta).apply {
                                    if (offline.name == null) {
                                        displayName("<gray>[Unknown]".mini)
                                    } else {
                                        displayName("<gold><bold>${offline.name}".mini)
                                    }

                                    owningPlayer = offline

                                    lore(listOf("<yellow>클릭하여 접속하기".mini))
                                }
                            }
                        }

                        onClickItem { _, _, item, _ ->
                            val player = item.first
                            val uuid = player.uniqueId.toString()

                            val world = WorldManager.getWorld(uuid)

                            if (world == null) {
                                sender.sendMessage("<red>플레이어를 찾지 못했습니다.".mini)
                                return@onClickItem
                            }

                            sender.teleport(Location(world, 0.5, 0.0, 0.5, 0f, 0f))
                        }
                    }

                    onClose {
                        val world = sender.world
                        val uuid = sender.uniqueId.toString()

                        if (uuid == world.name) {
                            sender.gameMode = GameMode.CREATIVE
                        } else {
                            sender.gameMode = GameMode.SPECTATOR
                        }

                        sender.clearActivePotionEffects()
                    }
                }

                sender.gameMode = GameMode.CREATIVE
                sender.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 0, true, false))
                sender.openFrame(frame)
            } else if (args.size == 1) {
                val name = args[0]
                val player = Bukkit.getOfflinePlayer(name)
                val uuid = player.uniqueId.toString()

                val world = WorldManager.getWorld(uuid)

                if (world == null) {
                    sender.sendMessage("<red>플레이어를 찾지 못했습니다.".mini)

                    return true
                }

                sender.teleport(Location(world, 0.5, 0.0, 0.5, 0f, 0f))
            }
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        if (args.size == 1) {
            return Bukkit.getOfflinePlayers().mapNotNull { it.name }.filter { it != sender.name && it.startsWith(args[0]) }
        }

        return emptyList()
    }
}