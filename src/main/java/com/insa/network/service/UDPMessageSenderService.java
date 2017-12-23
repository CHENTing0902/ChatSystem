package com.insa.network.service;

import com.insa.message.MessageType;
import com.insa.model.Node;
import com.insa.model.Peer;
import com.insa.network.handler.UDPSenderHandler;

public class UDPMessageSenderService implements MessageSenderService {

    @Override
    public void sendMessageOn(Peer peer, byte[] message, MessageType type) throws Exception {
        Thread t = new Thread ( new UDPSenderHandler(peer, message, type));
        t.start();
    }

    public void sendBroadcast(Node node) throws Exception {
        Thread t = new Thread (new UDPSenderHandler(node));
        t.start();
    }
}
