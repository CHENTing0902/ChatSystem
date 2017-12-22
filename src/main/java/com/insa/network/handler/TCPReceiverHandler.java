package com.insa.network.handler;

import com.insa.Message.MessageFactory;
import com.insa.model.Node;
import com.insa.model.Peer;
import com.insa.network.IncomingMessageListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiverHandler implements  Runnable{

    Node node;
    ServerSocket serverSocket;
    Socket chatSocket;
    BufferedInputStream stream;

    public TCPReceiverHandler(Node node) throws IOException {
        serverSocket = new ServerSocket(Peer.PORT_TCP);
        chatSocket = serverSocket.accept();
        stream = new BufferedInputStream(chatSocket.getInputStream());
    }

    @Override
    public void run() {
        try{
            while (true) {
                byte[] message = new byte[50];
                int lenMessage = stream.read(message);

                System.out.println("CALL TCP Listener handler run");
                System.out.println(lenMessage);

                MessageFactory messageFactory =
                        new MessageFactory(this.node,
                                chatSocket.getInetAddress().getHostAddress(),
                                Peer.PORT_TCP);
                messageFactory.sortMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
