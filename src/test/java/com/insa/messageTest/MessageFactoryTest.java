package com.insa.messageTest;

import com.insa.message.MessageFactory;
import com.insa.message.messageHandler.ChatHandler;
import com.insa.message.messageHandler.InfoHandler;
import com.insa.message.messageHandler.JoinHandler;
import com.insa.model.Node;
import com.insa.model.Peer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.UnknownHostException;

public class MessageFactoryTest {

    private MessageFactory messageFactory;

    private Node node;
    private String ipAddress;
    private int port;

    private Peer peer;

    @Before
    public void setUp() throws UnknownHostException {
        this.peer = new Peer("lala", "localhost");
        this.node = new Node(peer);

        this.ipAddress = "localhost";
        this.port = 6666;

        this.messageFactory = new MessageFactory(node, ipAddress, port);
    }

    @Test
    public void testJoin() throws UnknownHostException {
        JoinHandler joinHandler = Mockito.mock(JoinHandler.class);
        messageFactory.callHandler("JOIN", "lili".getBytes());
        Mockito.verify(joinHandler);
    }

    @Test
    public void testInfo() throws UnknownHostException {
        InfoHandler infoHandler = Mockito.mock(InfoHandler.class);
        messageFactory.callHandler("INFO", "lili".getBytes());
        Mockito.verify(infoHandler);
    }

    @Test
    public void testChat() throws UnknownHostException {
        ChatHandler chatHandler = Mockito.mock(ChatHandler.class);
        messageFactory.callHandler("MESS", "lili".getBytes());
        Mockito.verify(chatHandler);
    }

}
