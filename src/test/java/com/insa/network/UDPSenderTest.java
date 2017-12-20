package com.insa.network;

import com.insa.Model.Peer;
import com.insa.network.service.UDPMessageSenderService;

import java.net.UnknownHostException;

public class UDPSenderTest {

    //TODO Socket Exception
    public static void main (String [] args ) throws UnknownHostException {

        Peer peer = new Peer("lili","localhost",Peer.PORT_COMMUNICATION);

        try {
            new UDPMessageSenderService().sendMessageOn(peer, "lalaland");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
