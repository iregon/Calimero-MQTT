package com.alessandro.calimero.lib;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.calimero.utils.config.parts.BuildingConfiguration;
import com.alessandro.calimero.utils.config.parts.DeviceConfiguration;
import com.alessandro.calimero.utils.config.parts.RoomConfiguration;
import com.alessandro.calimero.utils.rxjava.ObservableList;
import com.alessandro.logger.Logger;
import com.alessandro.mqtt.client.MqttConnectionHandler;
import com.alessandro.mqtt.client.MqttMessageExtended;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Tornesello
 */

public class MqttToKnx {

    private MqttConnectionHandler connectionHandler;
    private List<MqttMessageExtended> messages = new ArrayList<MqttMessageExtended>();

    public MqttToKnx(MqttConnectionHandler connectionHandler, InstallationConfiguration config) {
        this.connectionHandler = connectionHandler;

        subscribeToTopics(config);
        startMessageObserver();
    }

    private void subscribeToTopics(InstallationConfiguration config) {
        config.getBuildingsList().forEach(building ->
                building.getRooms().forEach(room ->
                        room.getDevices().forEach(device -> {
                            String topic = getTopic(building, room, device);
                            connectionHandler.subscribe(topic);
                            Logger.getInstance().info(MessageFormat.format("Subscribed to {0}", topic));
                        })));
    }

    private String getTopic(BuildingConfiguration building, RoomConfiguration room, DeviceConfiguration device) {
        return MessageFormat.format("{0}/{1}/{2}",
                normalizeName(building.getName()),
                normalizeName(room.getName()),
                normalizeName(device.getName())
        );
    }

    private String normalizeName(String name) {
        return name.replaceAll(" ", "_");
    }

    private void startMessageObserver() {
        ObservableList<MqttMessageExtended> messageList = connectionHandler.getObservableMessageList();
        messageList.getObservable().subscribe((change) -> {
            sendTelegramToKnx();
        });
    }

    private void sendTelegramToKnx() {}
}
