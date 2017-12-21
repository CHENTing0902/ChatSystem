package com.insa;

import com.insa.model.Node;
import com.insa.model.Peer;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

public class NodeTest {

    private Peer peer1;
    private Peer peer2;
    private Peer peer3;
    private Peer peer4;
    private Peer peer;

    private Node node;

    public void init() throws UnknownHostException {

        this.peer1 = new Peer("lala", "10.0.0.1");
        this.peer2 = new Peer("lili", "10.0.0.2");
        this.peer3 = new Peer("lulu", "10.0.0.3");
        this.peer4 = new Peer("lulu2", "10.0.0.3");

        this.peer = new Peer("moi", "10.0.0.4");
        this.node = new Node(peer);

    }

    @Test
    public void addAllPeers() throws UnknownHostException {

        init();

        this.node.updatePeersList(this.peer1);
        System.out.println(this.node.toString());
        this.node.updatePeersList(this.peer2);
        System.out.println(this.node.toString());
        this.node.updatePeersList(this.peer3);
        System.out.println(this.node.toString());
        this.node.updatePeersList(this.peer4);
        System.out.println(this.node.toString());

    }


}
