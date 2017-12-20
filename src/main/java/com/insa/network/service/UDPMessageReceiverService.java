package com.insa.network.service;

import com.insa.network.IncomingMessageListener;
import com.insa.network.handler.UDPReceiverHandler;

public class UDPMessageReceiverService implements MessageReceiverService {
    @Override
    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {

        Thread t = new Thread(new UDPReceiverHandler(incomingMessageListener));
        t.start();
    }
}