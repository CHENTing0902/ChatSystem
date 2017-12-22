package com.insa.ui;

import com.insa.model.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;

public class Noti extends JFrame {

    private Node node;
    private Peer peer;
    private String message;

    public Noti(Node node, Peer peer,String message){

        this.node = node;
        this.peer = peer;
        this.message = message;

        JLabel notiLabel = new JLabel("you have 1 new message from " + peer.getPseudonyme());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> onCancelButtonClicked());

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> onConfirmButtonClicked());

        Panel formPanel = new Panel();
        formPanel.add(notiLabel);
        formPanel.add(confirmButton);
        formPanel.add(cancelButton);

        add(formPanel, NORTH);
        add(cancelButton, WEST);
        add(confirmButton, EAST);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void onCancelButtonClicked() {
        this.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();
    }

    private void onConfirmButtonClicked() {

        Chat chat = new Chat(null,this.node,this.peer);
        this.node.setChatWindowForPeer(peer,chat);

        this.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();

        chat.updateChatArea(this.message, false);
        chat.display();
    }
}
