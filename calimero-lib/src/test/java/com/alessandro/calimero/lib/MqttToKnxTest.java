package com.alessandro.calimero.lib;

import com.alessandro.knx.client.KnxConnectionHandler;
import com.alessandro.mqtt.client.MqttConnectionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class MqttToKnxTest {

    @Mock
    private MqttConnectionHandler mqttHandler;

    @Mock
    private KnxConnectionHandler knxHandler;

    private MqttToKnx mqttToKnx = new MqttToKnx(mqttHandler, knxHandler);

    @Test
    void getCommandGroupAddressFromMqttTopic() {
        assertEquals(mqttToKnx.getCommandGroupAddressFromMqttTopic("hello/1/1/0"), "1/1/0");
        assertEquals(mqttToKnx.getCommandGroupAddressFromMqttTopic("hello/ciao/1/2/0"), "1/2/0");
    }
}