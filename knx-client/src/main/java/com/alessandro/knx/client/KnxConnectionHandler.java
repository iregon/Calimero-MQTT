package com.alessandro.knx.client;

import com.alessandro.calimero.utils.config.InstallationConfiguration;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author Alessandro Tornesello
 */

public class KnxConnectionHandler {

    private InetSocketAddress local;
    private InetSocketAddress server;
    private KnxTunneling tunneling;

    public KnxConnectionHandler(InstallationConfiguration config) {
        setSocketAddresses(config);

        tunneling = new KnxTunneling(local, server);
        try {
            tunneling.createTunnelling();
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

        server = getRemoteSocketAddrees(config);
    }

    private InetSocketAddress getLocalSocketAddrees(InstallationConfiguration config) throws UnknownHostException {
        return new InetSocketAddress(
                InetAddress.getLocalHost(),
                new Integer(config.getLocalPort()));
    }

    private InetSocketAddress getRemoteSocketAddrees(InstallationConfiguration config) {
        return new InetSocketAddress(
                config.getKnxServerAddress(),
                new Integer(config.getKnxServerPort()));
    }

    public void sendTelegram(GroupAddress groupAddress, String s) {
        try {
            tunneling.getCommunicator().write(groupAddress, s);
        } catch (KNXException e) {
            e.printStackTrace(); // TODO add logger
        }
    }

}
