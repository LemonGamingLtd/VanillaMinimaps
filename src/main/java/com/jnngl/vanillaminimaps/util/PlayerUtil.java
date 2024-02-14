package com.jnngl.vanillaminimaps.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Player utilities.
 */
public final class PlayerUtil {

    private static final String FLOODGATE_PLUGIN_NAME = "floodgate";

    /**
     * Check if the given player is utilizing a Bedrock client.
     * Beware that we use floodgate as the provider to check this!
     *
     * @param player Player to check.
     * @return {@code true} if the player is using a Bedrock client, {@code false} otherwise.
     */
    public static boolean isBedrockPlayer(@NotNull Player player) {
        return isBedrockPlayer(player.getUniqueId());
    }

    /**
     * Check if the given player identifier is that of a Bedrock client.
     * Beware that we use floodgate as the provider to check this!
     *
     * @param playerId UUID of the player to check.
     * @return {@code true} if the player is using a Bedrock client, {@code false} otherwise.
     */
    public static boolean isBedrockPlayer(@NotNull UUID playerId) {
        if (!Bukkit.getPluginManager().isPluginEnabled(FLOODGATE_PLUGIN_NAME)) {
            return false;
        }
        return FloodgateApi.getInstance().isFloodgatePlayer(playerId);
    }
}
