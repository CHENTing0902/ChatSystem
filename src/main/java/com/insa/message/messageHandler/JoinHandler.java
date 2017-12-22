package com.insa.message.messageHandler;

import com.insa.message.MessageType;
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

            new UDPMessageSenderService().sendMessageOn(peer,this.node.userName().getBytes(), MessageType.INFO);

            System.out.println ("CALL IN JoinHandler :\nRECEIVED : " + peer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

