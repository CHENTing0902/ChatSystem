package com.insa.Message;

import com.insa.model.Node;

public class ChatHandler implements Runnable {

    private Node node;
    private String message;
    private String ipAddress;
    private int port;

    public ChatHandler(Node node, String data, String ipAddress, int port){
        this.node = node;
        this.message = data;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("CALL IN ChatHandler :\nI received : " + this.message + "\nfrom " + this.ipAddress+"\nThank you");
    }

}
