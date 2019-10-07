/**
 * @author Alessandro Tornesello
 */

public class KnxToMqtt {

    private MqttConnectionHandler connectionHandler;

    public KnxToMqtt(MqttConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }
}
