/**
 * @author Alessandro Tornesello
 */

package utils;

import java.util.UUID;

public class UUIDGenerator {
    private static UUID uuid;

    public static String generateRandom() {
        uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
