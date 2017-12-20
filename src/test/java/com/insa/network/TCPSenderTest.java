package com.insa.network;

import com.insa.Model.Peer;
import com.insa.network.service.TCPMessageSenderService;

import java.net.UnknownHostException;

public class TCPSenderTest {

    public static void main(String[] args) throws UnknownHostException {

        Peer peer = new Peer("lili","localhost",Peer.PORT_COMMUNICATION);

        try {
            new TCPMessageSenderService().sendMessageOn(peer, "lalaland");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}