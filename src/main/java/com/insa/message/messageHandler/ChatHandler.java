package com.insa.message.messageHandler;

import com.insa.model.*;
import com.insa.ui.Chat;
import com.insa.ui.Noti;

public class ChatHandler implements Runnable {

    private Node node;
    private String message;
    private String ipAddress;

    public ChatHandler(Node node, String data, String ipAddress){
        this.node = node;
        this.message = data;
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        System.out.println("CALL IN ChatHandler :\nReceived : " + this.message + "\nfrom " + this.ipAddress+"\n");

        Chat chat = this.node.getChatWindowForPeer(ipAddress);

        if (chat == null){
            Peer peer = this.node.findPeerByIpAddress(ipAddress);

            Noti noti = new Noti (node, peer,message);
            noti.display();
        }
        else{
            chat.updateChatArea(this.message,false);
        }
    }

}
