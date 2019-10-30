package com.alessandro.knx.client;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import com.alessandro.logger.Logger;
import tuwien.auto.calimero.*;
import tuwien.auto.calimero.datapoint.*;

import java.net.*;
import java.text.MessageFormat;
import java.util.*;

public class KnxConnectionHandler{

    private InetSocketAddress local;
    private InetSocketAddress server;

    private KnxTunneling tunneling;
    private KnxTelegramListener listener;
    private KnxTelegramSender sender;

    public KnxConnectionHandler(InstallationConfiguration config) {
        setSocketAddresses(config);

        Logger.info(MessageFormat.format(
                "Connectiong to KNX server {0}:{1} ...",
                server.getAddress().toString(),
                Integer.toString(server.getPort())
        ));
        tunneling = new KnxTunneling(local, server);
        try {
            tunneling.createTunnelling();
            listener = new KnxTelegramListener();
            tunneling.getCommunicator().addProcessListener(listener);
            sender = new KnxTelegramSender(tunneling.getCommunicator());
        } catch (KNXException e) {
            Logger.info("Impossible to connect to KNX server.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setSocketAddresses(InstallationConfiguration config) {
        try {
            local = getLocalSocketAddrees(config);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        server = getRemoteSocketAddress(config);
    }

    private InetSocketAddress getLocalSocketAddrees(InstallationConfiguration config) throws UnknownHostException {
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

        if(isState) dp = new StateDP(groupAddress, UUID.randomUUID().toString());
        else dp = new CommandDP(groupAddress, UUID.randomUUID().toString());

        dp.setDPT(0, dpt);
        sender.sendDataToDevice(dp, s);
    }

    public void addTelegramObserver(Observer o) {
        listener.addObserver(o);
    }
}
