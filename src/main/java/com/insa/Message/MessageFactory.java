package com.insa.Message;

import com.insa.model.Node;

import java.io.IOException;
import java.net.UnknownHostException;

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

    private void callMessageTraiter(String type, String data) throws UnknownHostException {
        System.out.println (type);

        MessageType messageType = MessageType.valueOf(type);

        switch (messageType){
            case JOIN :
                System.out.println ("case join");
                Thread joinThread = new Thread(new JoinHandler(node,data,ipaddress,port));
                joinThread.start();
                break;
            case MESS :
                //TODO messageHandler
                System.out.println ("case message");
                Thread messThread = new Thread(new ChatHandler(node,data,ipaddress,port));
                messThread .start();
                break;
            case INFO:
                System.out.println ("case INFO");
                Thread infoThread = new Thread(new InfoHandler(node,data,this.ipaddress,port));
                infoThread .start();
                break;
            default :
                System.out.println("unknown type message");
                break;
        }
    }
}
