package com.insa.network.service;

import com.insa.Model.Node;
import com.insa.Model.Peer;
import com.insa.network.handler.TCPSenderHandler;

public class TCPMessageSenderService implements MessageSenderService {

    @Override
    public void sendMessageOn(Peer peer, String message) throws Exception {
        Thread t = new Thread(new TCPSenderHandler(peer,message));
        t.start();
    }
}
