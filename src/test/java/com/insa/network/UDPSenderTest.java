package com.insa.network;

import com.insa.Message.MessageType;
import com.insa.model.Peer;
import com.insa.network.service.UDPMessageSenderService;

import java.net.UnknownHostException;

public class UDPSenderTest {

    //TODO Socket Exception
    public static void main (String [] args ) throws UnknownHostException {

        Peer peer = new Peer("lili","localhost",Peer.PORT_COMMUNICATION);

        try {
            new UDPMessageSenderService().sendMessageOn(peer, "lalaland".getBytes(), MessageType.MESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
