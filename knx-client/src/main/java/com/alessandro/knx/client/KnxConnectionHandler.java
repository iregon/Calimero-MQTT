package com.alessandro.knx.client;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.logger.Logger;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.datapoint.CommandDP;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.datapoint.StateDP;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Observer;
import java.util.UUID;

public class KnxConnectionHandler {

    private InetSocketAddress local;
    private InetSocketAddress server;

    private KnxTunneling tunneling;
    private KnxTelegramListener listener;
    private KnxTelegramSender sender;

    private InstallationConfiguration configuration;

    // TRUE if is connected to KNX server, else FALSE
    private boolean isConnected = false;

    public KnxConnectionHandler(InstallationConfiguration configuration) {
        this.configuration = configuration;
    }

    public void connect() {
        setSocketAddresses(this.configuration);

        Logger.info(MessageFormat.format(
                "Connectiong to KNX server {0}:{1} ...",
                server.getAddress().toString(),
                Integer.toString(server.getPort())
        ));

        createTunneling();

        listener = new KnxTelegramListener();
        tunneling.getCommunicator().addProcessListener(listener);
        sender = new KnxTelegramSender(tunneling.getCommunicator());

        isConnected = true;
    }

    private void createTunneling() {
        tunneling = new KnxTunneling(local, server);
        try {
            tunneling.createTunnelling();
        } catch (KNXException | InterruptedException e) {
            Logger.info(MessageFormat.format("Impossible to connect to KNX server: {0}", e.getMessage()));
        }
    }

    private void setSocketAddresses(InstallationConfiguration config) {
        try {
            local = getLocalSocketAddress(config);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        server = getRemoteSocketAddress(config);
    }

    private InetSocketAddress getLocalSocketAddress(InstallationConfiguration config) throws UnknownHostException {
        return new InetSocketAddress(
                InetAddress.getLocalHost(),
                new Integer(config.getLocalPort()));
    }

    private InetSocketAddress getRemoteSocketAddress(InstallationConfiguration config) {
        return new InetSocketAddress(
                config.getKnxServerAddress(),
                new Integer(config.getKnxServerPort()));
    }

    public void sendTelegram(GroupAddress groupAddress, String dpt, String s, boolean isState) {
        Datapoint dp;

        if (isState) dp = new StateDP(groupAddress, UUID.randomUUID().toString());
        else dp = new CommandDP(groupAddress, UUID.randomUUID().toString());

        dp.setDPT(0, dpt);
        sender.sendDataToDevice(dp, s);
    }

    public void addTelegramObserver(Observer o) {
        if (isConnected) listener.addObserver(o);
    }
}
