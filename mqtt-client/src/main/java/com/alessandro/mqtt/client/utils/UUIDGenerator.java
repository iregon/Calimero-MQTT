/**
 * @author Alessandro Tornesello
 */

package com.alessandro.mqtt.client.utils;

import java.util.UUID;

public class UUIDGenerator {
    private static UUID uuid;

    public static String generateRandom() {
        uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
