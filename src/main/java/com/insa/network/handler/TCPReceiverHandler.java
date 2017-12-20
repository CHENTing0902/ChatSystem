package com.insa.network.handler;

import com.insa.model.Peer;
import com.insa.network.IncomingMessageListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiverHandler implements  Runnable{

    ServerSocket serverSocket;
    Socket chatSocket;
    BufferedInputStream stream;
    IncomingMessageListener incomingMessageListener;

    public TCPReceiverHandler(IncomingMessageListener incomingMessageListener) throws IOException {
        serverSocket = new ServerSocket(Peer.PORT_COMMUNICATION);
        chatSocket = serverSocket.accept();
        stream = new BufferedInputStream(chatSocket.getInputStream());
        this.incomingMessageListener = incomingMessageListener;
    }

    @Override
    public void run() {
        try{
            byte[] message = new byte[50];
            int lenMessage = stream.read(message);
            System.out.println(lenMessage);
            incomingMessageListener.onNewIncomingMessage(message);

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
