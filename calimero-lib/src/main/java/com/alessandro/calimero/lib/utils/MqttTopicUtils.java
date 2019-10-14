package com.alessandro.calimero.lib.utils;

import com.alessandro.calimero.utils.config.parts.DeviceConfiguration;
import com.alessandro.calimero.utils.config.parts.FloorConfiguration;
import com.alessandro.calimero.utils.config.parts.GroupAddress;
import com.alessandro.calimero.utils.config.parts.RoomConfiguration;

import java.text.MessageFormat;

public class MqttTopicUtils {

    public static String getTopic(FloorConfiguration floor,
                                  RoomConfiguration room,
                                  DeviceConfiguration device,
                                  GroupAddress groupAddress) {
        return MessageFormat.format("{0}/{1}/{2}/{3}",
                normalizeName(floor.getLabel()),
                normalizeName(room.getLabel()),
                normalizeName(device.getLabel()),
                groupAddress.getAddress()
        );
    }

    private static String normalizeName(String name) {
        String normalized = name.replaceAll(" ", "_");
        return normalized;
    }
}
