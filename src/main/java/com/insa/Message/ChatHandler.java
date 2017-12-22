package com.insa.Message;

import com.insa.model.Node;
import com.insa.model.Peer;
import com.insa.ui.Chat;

import javax.swing.*;

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

        //TODO update chat window
        Chat chat = this.node.getChatWindowForPeer(ipAddress);

        if (chat == null){
            Peer peer = this.node.findPeerByIpAddress(ipAddress);
            Chat chatd = new Chat(null,node,peer);
            Object[] options = {chatd , "CANCEL" };
            JOptionPane.showOptionDialog(null, "Click OK to open chat", "you have 1 new message",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
        }
        else{
            chat.updateChatArea(this.message,false);
        }
    }

}
