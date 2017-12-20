package com.insa.Message;

import com.insa.Model.Node;

import java.io.IOException;

public class MessageFactory {

    Node node;
    String ipaddress;
    int port;

    public MessageFactory(Node node){
        this.node = node;
    }

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

        int intLen = Message.byteArrayToInt(len);
        System.out.println(intLen);
        data = new byte[intLen];

        for (int i=0; i<data.length; i++) data[i] = message[i+8];

        String typeString = new String (type);
        String dataString = new String (data);
        System.out.println(typeString + " " + dataString );

        callMessageTraiter(new String(type), new String(data));
    }

    private void callMessageTraiter(String type, String data){
        System.out.println (type);

        MessageType messageType = MessageType.valueOf(type);

        switch (messageType){
            case JOIN :
                System.out.println ("case join");
                Thread t = new Thread(new BroadcastMessageHandler(node,data,ipaddress,port));
                t.start();
                break;
            default :
                System.out.println("unknown type message");
                break;
        }
    }
}
