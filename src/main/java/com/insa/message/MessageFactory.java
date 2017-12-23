package com.insa.message;

import com.insa.message.messageHandler.*;
import com.insa.file.FileHandler;

import com.insa.model.Node;

import java.io.IOException;
import java.net.UnknownHostException;

public class MessageFactory {

    private Node node;
    private String ipaddress;
    private int port;


    public MessageFactory(Node node, String ipaddress, int port){
        this.node = node;
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public void sortMessage(byte[] message) throws IOException {

        byte [] type;
        byte [] data;

        if (message.length < 4){
            throw new IOException("EOF in PeerMessage constructor : type");
        }
        if (message.length < 8){
            throw new IOException("EOF in PeerMessage constructor : len data");
        }

        type = new byte[4];
        byte[] len = new byte[4];
        for (int i = 0; i < 4; i++) type[i]= message[i];
        for (int i = 0; i < 4; i++) len[i] = message[i+4];

        int intLen = MessageTreatment.byteArrayToInt(len);
        System.out.println(intLen);
        data = new byte[intLen];

        for (int i=0; i<data.length; i++) data[i] = message[i+8];

        callHandler(new String(type), data);
    }

    public void callHandler(String type, byte [] data) throws UnknownHostException {

        MessageType messageType = MessageType.valueOf(type);

        switch (messageType){
            case JOIN :
                System.out.println ("CALL IN callHandler : case join");
                Thread joinThread = new Thread(new JoinHandler(node,new String(data),ipaddress,port));
                joinThread.start();
                break;
            case MESS :
                System.out.println ("CALL IN callHandler : case message");
                Thread messThread = new Thread(new ChatHandler(node,new String(data),ipaddress));
                messThread .start();
                break;
            case INFO:
                System.out.println ("CALL IN callHandler : case INFO");
                Thread infoThread = new Thread(new InfoHandler(node,new String(data),this.ipaddress,port));
                infoThread .start();
                break;
            case FILE:
                System.out.println ("CALL IN callHandler : case FILE");
                Thread fileThread = new Thread(new FileHandler(node,data,this.ipaddress));
                fileThread .start();
                break;

            default :
                System.out.println("CALL IN callHandler : unknown type message");
                break;
        }
    }
}
