package com.activation.premium.managers

import org.bukkit.*
import org.bukkit.generator.ChunkGenerator
import java.util.*

object WorldManager {
    fun getWorld(uuid: String): World? {
        var world = Bukkit.getWorld(uuid)

        if (world == null) {
            val creator = WorldCreator(uuid)

            world = creator.generator(VoidGenerator())
                .generateStructures(false)
                .environment(World.Environment.NORMAL)
                .createWorld()
        }

        if (world != null) {
            world.worldBorder.size = 201.0
            world.worldBorder.setCenter(0.0, 0.0)
            world.setSpawnLocation(0, 0, 0)
            world.getBlockAt(0, -1, 0).type = Material.BEDROCK
            world.weatherDuration = 0
            world.setStorm(false)
            world.isThundering = false
            world.time = 0

            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false)
            world.setGameRule(GameRule.FALL_DAMAGE, false)
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
            world.setGameRule(GameRule.KEEP_INVENTORY, true)
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false)
            world.setGameRule(GameRule.SPAWN_RADIUS, 0)
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false)
            world.setGameRule(GameRule.DO_FIRE_TICK, false)
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false)
            world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false)
            world.setGameRule(GameRule.DO_TRADER_SPAWNING, false)
            world.setGameRule(GameRule.DO_PATROL_SPAWNING, false)
        }

        return world
    }

    private class VoidGenerator: ChunkGenerator() {
        override fun generateChunkData(world: World, random: Random, x: Int, z: Int, biome: BiomeGrid): ChunkData {
            return createChunkData(world)
        }

        override fun canSpawn(world: World, x: Int, z: Int): Boolean {
            return true
        }

        override fun getFixedSpawnLocation(world: World, random: Random): Location {
            return Location(world, 0.5, 0.0, 0.5, 0f, 0f)
        }
    }
}