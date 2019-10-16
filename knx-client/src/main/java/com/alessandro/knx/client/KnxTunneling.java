package com.alessandro.knx.client;// https://github.com/calimero-project/introduction/blob/master/src/main/java/CreateTunnelingLink.java

import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.KNXMediumSettings;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

import java.net.InetSocketAddress;

public class KnxTunneling {

    private InetSocketAddress local;

    /**
     * Specifies the KNXnet/IP server to access the KNX network, insert your server's actual host name or IP address,
     * e.g., "192.168.1.20". The default port is where most servers listen on for new connection requests.
     */
    private InetSocketAddress server;

    private ProcessCommunicator communicator;

    public KnxTunneling(InetSocketAddress local, InetSocketAddress server) {
        this.local = local;
        this.server = server;
    }

    /**
     * Create link to a KNXnet/IP server.
     *
     * <pre>
     * {@code
     * InetSocketAddress local = new InetSocketAddress("192.168.1.10", 0);
     * InetSocketAddress server = new InetSocketAddress("myKnxServer.myHome", KNXnetIPConnection.DEFAULT_PORT);
     *
     * KnxTunneling tunnel = new KnxTunneling(local, server);
     *
     * try {
     *     KNXNetworkLink link = tunnel.createTunnelling()
     * } catch (KNXException e) {
     *     ...
     * }catch (InterruptedException e) {
     *     ...
     * }
     * }
     * </pre>
     *
     * @return Link to the server.
     * @throws KNXException
     * @throws InterruptedException
     */
    public KNXNetworkLinkIP createTunnelling() throws KNXException, InterruptedException {
        return createTunnelling(false, TPSettings.TP1);
    }

    public KNXNetworkLinkIP createTunnelling(boolean useNAT) throws KNXException, InterruptedException {
        return createTunnelling(useNAT, TPSettings.TP1);
    }

    public KNXNetworkLinkIP createTunnelling(boolean useNAT, KNXMediumSettings settings) throws KNXException, InterruptedException {
        KNXNetworkLinkIP link = KNXNetworkLinkIP.newTunnelingLink(local, server, useNAT, settings);
        communicator = new ProcessCommunicatorImpl(link);
        return link;
    }

    public ProcessCommunicator getCommunicator() {
        return communicator;
    }
}
