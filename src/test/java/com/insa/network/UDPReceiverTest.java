package com.insa.network;

import com.insa.model.Peer;
import com.insa.network.service.UDPMessageReceiverService;

public class UDPReceiverTest {
    public static void main(String[] args) {

        IncomingMessageTreater incomingMessageTreater = new IncomingMessageTreater();
        try {
            new UDPMessageReceiverService().listenOnPort(Peer.PORT_COMMUNICATION, incomingMessageTreater);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
