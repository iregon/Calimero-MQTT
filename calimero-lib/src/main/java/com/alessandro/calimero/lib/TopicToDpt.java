package com.alessandro.calimero.lib;

import java.util.HashMap;
import java.util.Map;

public class TopicToDpt {

    private static Map<String, String> dps = new HashMap<>();

    public static void add(String topic, String dpt) {
        dps.put(topic, dpt);
    }

    public static String getDptFromMqttTopic(String topic) {
        return dps.get(topic);
    }
}
