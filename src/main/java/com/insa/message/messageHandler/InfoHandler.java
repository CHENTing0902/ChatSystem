package com.insa.message.messageHandler;

import com.insa.model.Node;
import com.insa.model.Peer;

import java.net.UnknownHostException;

public class InfoHandler implements Runnable {

    private Node node;
    private Peer peer;

    public InfoHandler(Node node, String data, String ipAddress, int port) throws UnknownHostException {
        this.node = node;
        this.peer = new Peer(data,ipAddress,port);
    }

    @Override
    public void run() {
        this.node.updatePeersList(peer);
    }
}
