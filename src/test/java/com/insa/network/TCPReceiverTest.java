package com.insa.network;

import com.insa.model.Peer;
import com.insa.network.service.TCPMessageReceiverService;

public class TCPReceiverTest {

    public static void main (String[] args) {
        IncomingMessageTreater incomingMessageTreater = new IncomingMessageTreater();

        try {
            new TCPMessageReceiverService ().listenOnPort(Peer.PORT_COMMUNICATION, incomingMessageTreater);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
