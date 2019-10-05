import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class MQTTMessageExtended extends MqttMessage {

    private String topic;

    // When the message is arrived
    private Date arrivalTime;

    public MQTTMessageExtended() {
    }

    public MQTTMessageExtended(byte[] payload) {
        super(payload);
    }

    public MQTTMessageExtended(String topic, MqttMessage msg) {
        super(msg.getPayload());
        super.setQos(msg.getQos());
        super.setId(msg.getId());
        this.topic = topic;
        arrivalTime = new Date();
    }

    public MQTTMessageExtended(String topic, byte[] payload, int qos) {
        super(payload);
        super.setQos(qos);
        this.topic = topic;
        arrivalTime = new Date();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayloadString() {
        return new String(getPayload());
    }

    public Date getDate() {
        return arrivalTime;
    }
}
