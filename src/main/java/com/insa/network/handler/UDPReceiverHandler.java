package com.insa.network.handler;

import com.insa.Model.Peer;
import com.insa.network.IncomingMessageListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPReceiverHandler implements Runnable {

    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    private IncomingMessageListener incomingMessageListener;

    public UDPReceiverHandler(IncomingMessageListener incomingMessageListener) throws SocketException {
        this.serverSocket = new DatagramSocket(Peer.PORT_COMMUNICATION);
        this.receivePacket = new DatagramPacket(new byte[50], 50);
        this.incomingMessageListener = incomingMessageListener;
    }

    @Override
    public void run() {
            try {
                System.out.printf("Listening on udp:%s:%d%n",
                        InetAddress.getLocalHost().getHostAddress(), Peer.PORT_COMMUNICATION);

                serverSocket.receive(receivePacket);
                incomingMessageListener.onNewIncomingMessage(receivePacket.getData());

                // now send acknowledgement packet back to sender
                InetAddress IPAddress = receivePacket.getAddress();

                String sendString = "polo";
                byte[] sendData = sendString.getBytes("UTF-8");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        IPAddress, receivePacket.getPort());

                serverSocket.send(sendPacket);
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

