package com.alessandro.knx.client;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.process.ProcessEvent;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Alessandro Tornesello
 */

public class KnxConnectionHandler implements Observer {

    private InetSocketAddress local;
    private InetSocketAddress server;

    private KnxTunneling tunneling;
    private KnxTelegramListener listener;
    private KnxTelegramSender sender;

    public KnxConnectionHandler(InstallationConfiguration config) {
        setSocketAddresses(config);

        tunneling = new KnxTunneling(local, server);
        try {
            tunneling.createTunnelling();
            listener = new KnxTelegramListener();
            listener.addObserver(this);
            sender = new KnxTelegramSender(tunneling.getCommunicator());
        } catch (KNXException e) {
            e.printStackTrace();
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

    public void sendTelegram(GroupAddress groupAddress, String s) {
        sender.sendDataToDevice(groupAddress, s);
    }

    @Override
    public void update(Observable o, Object arg) {
        ProcessEvent l = (ProcessEvent) arg;
        System.out.println("Group address is " + l.toString() + "and state is " + new String(l.getASDU()));
    }
}
