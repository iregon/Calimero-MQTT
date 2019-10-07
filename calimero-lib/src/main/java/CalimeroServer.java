/**
 * @author Alessandro Tornesello
 */

public class CalimeroServer {

    private MqttConnectionHandler connectionHandler;
    private MqttToKnx mqttToKnx;
    private KnxToMqtt knxToMqtt;

    public CalimeroServer() {
        connectionHandler = new MqttConnectionHandler();
        mqttToKnx = new MqttToKnx(connectionHandler);
        knxToMqtt = new KnxToMqtt(connectionHandler);

        connectionHandler.connect(new ConnectionProfile("127.0.0.1", "5000"));
    }
}
