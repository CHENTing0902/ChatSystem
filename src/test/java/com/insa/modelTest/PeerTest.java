package com.insa.modelTest;

import com.insa.model.Peer;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class PeerTest {

    private Peer peer;

    @Before
    public void setUp() throws UnknownHostException {
        this.peer = new Peer("lala", "localhost");

    }

    @Test
    public void testPseudonyme() {
        assertEquals("lala", this.peer.getPseudonyme());
    }

    @Test
    public void testHost() {
        assertEquals("localhost", this.peer.getHost());

    }

    @Test
    public void testPort() {
        assertEquals(6666, this.peer.getPort());

    }

}
