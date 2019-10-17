package com.alessandro.calimero.lib;

import java.util.HashMap;
import java.util.Map;

public class TopicToDpt {

    // Map with relation between MQTT topic and DPT of the connected telegram
    private static Map<String, String> dps = new HashMap<>();

    /**
     * Associates the MQTT topic with the DPT.
     * @param topic MQTT topic.
     * @param dpt Telegram DPT connected to the topic.
     */
    public static void add(String topic, String dpt) {
        dps.put(topic, dpt);
    }

    /**
     * Get the DPT associated to a MQTT topic.
     * @param topic MQTT topic.
     * @return Telegram DPT of the topic.
     */
    public static String getDptFromMqttTopic(String topic) {
        return dps.get(topic);
    }
}
