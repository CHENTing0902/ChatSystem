package com.insa.network.handler;

import com.insa.Message.Message;
import com.insa.Model.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSenderHandler implements Runnable {

    private DatagramSocket senderSocket;
    private DatagramPacket datagramPacket;


    // TODO create message class
    public UDPSenderHandler(Peer peer, String message) throws IOException {
        byte[] c = Message.builtMessage("MESS",message);

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
        byte[] c = Message.builtMessage("JOIN",node.getPeer().getPseudonyme());

        senderSocket = new DatagramSocket();
        datagramPacket = new DatagramPacket(c,c.length);
        datagramPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        datagramPacket.setPort(Peer.PORT_COMMUNICATION); //6666
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

