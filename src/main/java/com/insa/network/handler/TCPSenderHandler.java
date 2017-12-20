package com.insa.network.handler;

import com.insa.Model.Peer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPSenderHandler implements Runnable {

    private Socket chatSocket;
    private PrintWriter writer;
    private String message;

    public TCPSenderHandler(Peer peer, String message) throws IOException {
        this.chatSocket = new Socket (peer.getHost(), peer.getPort());
        this.writer = new PrintWriter(chatSocket.getOutputStream());
        this.message = message;
    }

    @Override
    public void run() {
        try {
            writer.println(message);
            writer.close();
            chatSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
