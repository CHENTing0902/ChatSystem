package com.insa;

import com.insa.Message.ChatHandler;
import com.insa.model.*;


import java.net.UnknownHostException;


public class ChatHandlerTest {

    public static void main (String[] args) throws UnknownHostException {

        Node node = new Node(new Peer ("lili", "localhost", Peer.PORT_COMMUNICATION));
        Peer peer1 = new Peer ("lala", "localhost", Peer.PORT_COMMUNICATION);

        node.updatePeersList(peer1);

        Thread t = new Thread(new ChatHandler(node, "message", peer1.getHost()));
        t.start();
    }

}
