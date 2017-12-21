package com.insa.Message;

import com.insa.model.*;
import com.insa.network.service.UDPMessageSenderService;

import java.io.IOException;
import java.net.UnknownHostException;

public class JoinHandler implements Runnable {

    private Node node;
    private Peer peer;

    public JoinHandler(Node node, String data, String ipAddress, int port) throws UnknownHostException {
        this.node = node;
        this.peer = new Peer(data, ipAddress, port );
    }

    public void run(){
        try {

            this.node.updatePeersList(peer);

            byte [] respond = Message.buildMessage("INFO",this.node.userName());

            new UDPMessageSenderService().sendMessageOn(peer,respond);

            System.out.println ("RECEIVED : " + peer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

