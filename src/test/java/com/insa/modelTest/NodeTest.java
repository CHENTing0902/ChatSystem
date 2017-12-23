package com.insa.modelTest;

import com.insa.model.Node;
import com.insa.model.Peer;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    private Node node;
    private Peer peer;

    private Peer newPeer;

    @Before
    public void setUp() throws UnknownHostException {
        this.peer = new Peer("lala", "localhost");
        this.node = new Node(this.peer);

        this.newPeer = new Peer("lili", "10.0.0.1");
        this.node.addPeer(newPeer);

    }

    @Test
    public void testFindPeer() {
        assertEquals(newPeer, this.node.findPeerByIpAddress("10.0.0.1"));

    }

    @Test
    public void testUpdatePeersLis() throws UnknownHostException {
        Peer p = new Peer("lulu", "10.0.0.1");
        this.node.updatePeersList(p);

        assertEquals("lulu", this.node.findPeerByIpAddress("10.0.0.1").getPseudonyme());

    }

}