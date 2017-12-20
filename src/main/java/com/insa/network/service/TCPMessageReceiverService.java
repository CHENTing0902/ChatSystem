package com.insa.network.service;

import com.insa.network.IncomingMessageListener;
import com.insa.network.handler.TCPReceiverHandler;

public class TCPMessageReceiverService implements MessageReceiverService {

    public void listenOnPort(int port, IncomingMessageListener incomingMessageListener) throws Exception {
        Thread t = new Thread(new TCPReceiverHandler(incomingMessageListener));
        t.start();
    }
}

