package com.alessandro.calimero.lib;

import com.alessandro.calimero.lib.utils.MqttTopicUtils;
import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.logger.Logger;
import com.alessandro.mqtt.client.MqttConnectionHandler;
import com.alessandro.mqtt.client.MqttMessageExtended;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

public class MqttToKnx implements Observer {

    private MqttConnectionHandler connectionHandler;

    public MqttToKnx(MqttConnectionHandler connectionHandler, InstallationConfiguration config) {
        this.connectionHandler = connectionHandler;

        subscribeToMqttTopics(config);
    }

    /**
     * Based on devices in InstallationConfiguration, subscribe to all topics.
     * @param config InstallationConfiguration with all device in installation.
     */
    private void subscribeToMqttTopics(InstallationConfiguration config) {
        config.getBuildingsList().forEach(building ->
                building.getRooms().forEach(room ->
                        room.getDevices().forEach(device -> {
                            String topic = MqttTopicUtils.getTopic(building, room, device);
                            connectionHandler.subscribe(topic);
                            Logger.getInstance().info(MessageFormat.format("Subscribed to {0}", topic));
                        })));
    }

    /**
     * This method is called whenever a new message from the MQTT broker need
     * to be sent to KNX installation
     * @param msg
     */
    private void sendTelegramToKnx(MqttMessageExtended msg) {
        System.out.println(msg.getPayloadString());
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        sendTelegramToKnx((MqttMessageExtended)arg);
    }
}
