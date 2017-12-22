package com.insa.network.handler;

import com.insa.Message.Message;
import com.insa.Message.MessageType;
import com.insa.model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderHandler implements Runnable {

    private DatagramSocket senderSocket;
    private DatagramPacket datagramPacket;


    // TODO create message class
    public UDPSenderHandler(Peer peer, byte[] d, MessageType type) throws IOException {

        String cString = new String(d);

        byte[] c = Message.buildMessage(type.getTypeAsString(), cString);

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

        byte[] c = Message.buildMessage("JOIN",node.getPeer().getPseudonyme());

        senderSocket = new DatagramSocket();
        datagramPacket = new DatagramPacket(c,c.length);
        datagramPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        datagramPacket.setPort(Peer.PORT_UDP); //6666
    }

    @Override
    public void run() {
        try {
            System.out.println("send ");
            senderSocket.send(datagramPacket);
            senderSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

