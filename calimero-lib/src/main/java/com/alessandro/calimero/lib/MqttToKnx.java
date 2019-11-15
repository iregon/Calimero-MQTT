package com.alessandro.calimero.lib;

import com.alessandro.calimero.lib.utils.MqttTopicUtils;
import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.calimero.utils.regex.RegexUtils;
import com.alessandro.knx.client.KnxConnectionHandler;
import com.alessandro.logger.Logger;
import com.alessandro.mqtt.client.MqttConnectionHandler;
import com.alessandro.mqtt.client.MqttMessageExtended;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXFormatException;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class MqttToKnx implements Observer {

    private MqttConnectionHandler mqttConnectionHandler;
    private KnxConnectionHandler knxConnectionHandler;

    public MqttToKnx(MqttConnectionHandler mqttConnectionHandler,
                     KnxConnectionHandler knxConnectionHandler) {
        this.mqttConnectionHandler = mqttConnectionHandler;
        this.knxConnectionHandler = knxConnectionHandler;
    }

    /**
     * Based on devices in InstallationConfiguration, subscribe to all topics.
     *
     * @param config InstallationConfiguration with all device in installation.
     */
    public void subscribeToMqttTopics(InstallationConfiguration config) {
        config.getFloorsList().forEach(floor ->
                floor.getRooms().forEach(room ->
                        room.getDevices().forEach(device ->
                                device.getGroupAddresses().forEach(groupAddress -> {
                                    String topic = MqttTopicUtils.getTopic(floor, room, device, groupAddress);
                                    mqttConnectionHandler.subscribe(topic);
                                    TelegramToTopicMatcher.addMatch(
                                            MqttTopicUtils.getBaseTopic(floor, room, device),
                                            device.getGroupAddresses().get(0).getDpt().getDisplayName(),
                                            device.getGroupAddresses().get(0).getAddress(),
                                            device.getGroupAddresses().get(0).getAddressStatus());
//                                    Logger.info(MessageFormat.format("Subscribed to {0}", topic));
                                }))));
    }

    /**
     * This method is called whenever a new message from the MQTT broker is received and need
     * to be sent to KNX installation
     *
     * @param msg message to be sent
     */
    private void sendTelegramToKnx(MqttMessageExtended msg) {
        try {
            GroupAddress address = new GroupAddress(getCommandGroupAddressFromMqttTopic(msg.getTopic()));
            Optional<String> dpt = TelegramToTopicMatcher.getDptFromCommandTopic(msg.getTopic());
            dpt.ifPresent(s -> knxConnectionHandler.sendTelegram(
                    address,
                    s,
                    msg.getPayloadString(),
                    false
            ));
            Logger.info(MessageFormat.format(
                    "Sended telegram to KNX Server: GA: {0}, value: {1}",
                    address.getMainGroup(),
                    msg.getPayloadString()));
        } catch (KNXFormatException e) {
            Logger.info(MessageFormat.format("Error in sending telegram to KNX Server: {0}", e.getMessage()));
        }
    }

    /**
     * Get command group address of a KNX group from the topic of a MQTT message.
     * Example:
     * MQTT topic: ground_floor/kitchen/light/0/0/1
     * last 3 digit are the group address, so:
     * KNX command group address: 0/0/1
     *
     * @param topic MQTT topic.
     * @return KNX command group address.
     */
    public String getCommandGroupAddressFromMqttTopic(String topic) {
        // getFirstMatch until third / from the end of the string
        return RegexUtils.getFirstMatch("[^\\/]*\\/[^\\/]*\\/[^\\/]*+$", topic).get();
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
        sendTelegramToKnx((MqttMessageExtended) arg);
    }
}
