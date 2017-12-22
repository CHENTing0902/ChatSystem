package com.insa.network.handler;

import com.insa.message.MessageTreatment;
import com.insa.message.MessageType;
import com.insa.model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderHandler implements Runnable {

    private DatagramSocket senderSocket;
    private DatagramPacket datagramPacket;

    public UDPSenderHandler(Peer peer, byte[] d, MessageType type) throws IOException {

        byte[] c = MessageTreatment.buildMessage(type.getTypeAsString(), d);

        senderSocket = new DatagramSocket();
        datagramPacket = new DatagramPacket(c,c.length);
        datagramPacket.setAddress(InetAddress.getByName(peer.getHost()));
        datagramPacket.setPort(peer.getPort());
    }

    /**
     *
     * constructor for broadcast send
     */
    public UDPSenderHandler(Node node) throws IOException {

        byte[] c = MessageTreatment.buildMessage("JOIN",node.userName().getBytes());

        senderSocket = new DatagramSocket();
        datagramPacket = new DatagramPacket(c,c.length);
        datagramPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        datagramPacket.setPort(Peer.PORT_UDP); //6666
    }

    @Override
    public void run() {
        try {
            System.out.println("CALL IN UDP Sender Handler : send ");
            senderSocket.send(datagramPacket);
            senderSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

