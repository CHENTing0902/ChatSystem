package com.insa.Message;

import com.insa.model.Node;
import com.insa.model.Peer;
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
        System.out.println("CALL IN ChatHandler :\nI received : " + this.message + "\nfrom " + this.ipAddress+"\nThank you");

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
