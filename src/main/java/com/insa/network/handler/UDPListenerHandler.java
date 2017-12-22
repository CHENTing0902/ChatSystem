package com.insa.network.handler;

import com.insa.Message.MessageFactory;
import com.insa.model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPListenerHandler implements Runnable {

    private Node node;
    private DatagramSocket datagramSocket;
    private DatagramPacket receivePacket;

    public UDPListenerHandler(Node node) throws SocketException {
        this.node = node;
        datagramSocket = new DatagramSocket(Peer.PORT_UDP); //6666
        this.receivePacket = new DatagramPacket(new byte[64],64);
    }

    public void run(){
        try {
            while (true) {
                datagramSocket.receive(receivePacket);

                System.out.println("CALL UDP Listener handler run");
                System.out.println(receivePacket.getData());

                MessageFactory messageFactory =
                        new MessageFactory(this.node,
                                receivePacket.getAddress().getHostAddress(),
                                Peer.PORT_UDP);
                messageFactory.sortMessage(receivePacket.getData());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
