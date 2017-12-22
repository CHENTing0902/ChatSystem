package com.insa.network.handler;

import com.insa.message.MessageFactory;
import com.insa.model.Node;
import com.insa.model.Peer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPListenerHandler implements  Runnable{

    Node node;
    ServerSocket serverSocket;
    Socket chatSocket;
    BufferedInputStream stream;

    public TCPListenerHandler(Node node) throws IOException {
        this.node = node;
        serverSocket = new ServerSocket(Peer.PORT_TCP);
        chatSocket = serverSocket.accept();
        stream = new BufferedInputStream(chatSocket.getInputStream());
    }

    @Override
    public void run() {
        try{
            byte[] message = new byte[50];
            int lenMessage = stream.read(message);

            System.out.println("CALL IN TCP Listener handler run");

            MessageFactory messageFactory =
                    new MessageFactory(this.node,
                            chatSocket.getInetAddress().getHostAddress(),
                            Peer.PORT_TCP);
            messageFactory.sortMessage(message);
            stream.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
