package com.jnngl.vanillaminimaps.util;

import com.comphenix.protocol.ProtocolLibrary;
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
    private static final String PROTOCOL_PLUGIN_NAME = "ProtocolLib";

    private static final int VALID_PROTOCOL_VERSION = 765; // Protocol version for 1.20.4 client

    /**
     * Check if the given player client is valid to dispatch a minimap.
     *
     * @param player Player to check.
     * @return {@code true} if the client is valid, {@code false} otherwise.
     */
    public static boolean isValidPlayerClient(@NotNull Player player) {
        return !isBedrockPlayer(player) && getPlayerProtocolVersion(player) >= VALID_PROTOCOL_VERSION;
    }

    /**
     * Get the protocol version for the given player.
     *
     * @param player Player to fetch protocol version for.
     * @return {@link Integer} protocol version, <a href="https://wiki.vg/Protocol_version_numbers">recommended read.</a>
     */
    public static int getPlayerProtocolVersion(@NotNull Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled(PROTOCOL_PLUGIN_NAME)) {
            return VALID_PROTOCOL_VERSION;
        }
        return ProtocolLibrary.getProtocolManager().getProtocolVersion(player);
    }

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
