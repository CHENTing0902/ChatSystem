package com.insa.network.service;

import com.insa.model.Peer;
import com.insa.network.handler.TCPSenderHandler;

public class TCPMessageSenderService implements MessageSenderService {

    @Override
    public void sendMessageOn(Peer peer, byte [] message) throws Exception {
        Thread t = new Thread(new TCPSenderHandler(peer,message));
        t.start();
    }
}
