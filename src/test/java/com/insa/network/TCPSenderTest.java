package com.insa.network;

import com.insa.Message.MessageType;
import com.insa.model.Peer;
import com.insa.network.service.TCPMessageSenderService;

import java.net.UnknownHostException;

public class TCPSenderTest {

    public static void main(String[] args) throws UnknownHostException {

        Peer peer = new Peer("lili","localhost",Peer.PORT_COMMUNICATION);

        try {
            new TCPMessageSenderService().sendMessageOn(peer, "lalaland".getBytes(), MessageType.MESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
