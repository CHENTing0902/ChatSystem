package com.insa.network.service;

import com.insa.model.Node;
import com.insa.model.Peer;
import com.insa.network.handler.UDPSenderHandler;

public class UDPMessageSenderService implements MessageSenderService {

    @Override
    public void sendMessageOn(Peer peer, String message) throws Exception {
        Thread t = new Thread ( new UDPSenderHandler(peer, message));
        t.start();
    }

    public void sendBroadcast(Node node) throws Exception {
        Thread t = new Thread (new UDPSenderHandler(node));
        t.start();
    }
}
