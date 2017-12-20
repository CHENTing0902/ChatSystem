package com.insa.network.handler;

import com.insa.Message.MessageFactory;
import com.insa.model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class BroadcastListenerHandler implements Runnable {

    public final static int PORT_BROADCAST = 8888 ;

    private Node node;
    private DatagramSocket datagramSocket;
    private DatagramPacket receivePacket;

    public BroadcastListenerHandler(Node node) throws SocketException {
        this.node = node;
        datagramSocket = new DatagramSocket(Peer.PORT_COMMUNICATION);
        this.receivePacket = new DatagramPacket(new byte[64],64);
    }

    public void updateNode(Node node){
        this.node = node;
    }

    public void run(){
        try {
            while (true) {
                datagramSocket.receive(receivePacket);

                MessageFactory messageFactory =
                        new MessageFactory(this.node,
                                receivePacket.getAddress().getHostAddress(),
                                receivePacket.getPort());
                messageFactory.sortMessage(receivePacket.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
