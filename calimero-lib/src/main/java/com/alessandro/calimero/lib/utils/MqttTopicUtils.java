package com.alessandro.calimero.lib.utils;

import com.alessandro.calimero.utils.config.parts.BuildingConfiguration;
import com.alessandro.calimero.utils.config.parts.DeviceConfiguration;
import com.alessandro.calimero.utils.config.parts.RoomConfiguration;

import java.text.MessageFormat;

public class MqttTopicUtils {

    public static String getTopic(BuildingConfiguration building, RoomConfiguration room, DeviceConfiguration device) {
        return MessageFormat.format("{0}/{1}/{2}",
                normalizeName(building.getName()),
                normalizeName(room.getName()),
                normalizeName(device.getName())
        );
    }

    private static String normalizeName(String name) {
        String normalized = name.replaceAll(" ", "_");
        return normalized;
    }
}
