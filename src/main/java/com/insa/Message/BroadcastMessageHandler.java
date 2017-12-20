package com.insa.Message;

import com.insa.Model.Node;
import com.insa.Model.Peer;

import java.net.UnknownHostException;

public class BroadcastMessageHandler implements Runnable {

    private Node node;
    private String peerPseudonyme;
    private String ipAddress;
    private int port;

    public BroadcastMessageHandler(Node node, String data, String ipAddress, int port) {
        this.node = node;
        this.peerPseudonyme = data;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void run(){

        try {
            Peer peer = new Peer(this.peerPseudonyme, ipAddress, port );

            if (!this.peerPseudonyme.equals(this.node.getPeer().getPseudonyme())) {
                this.node.addPeer(peer);
            }

            System.out.println ("RECEIVED : " + peer.toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

