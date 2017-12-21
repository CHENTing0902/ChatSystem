package com.insa.ui;

import com.insa.model.*;
import com.insa.network.handler.ListenerHandler;
import com.insa.network.service.UDPMessageSenderService;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;

public class Login extends JFrame {

    private static final int DEFAULT_TEXT_FIELD_WIDTH = 10;
    private Node node;
    private ListenerHandler listenerHandler;

    private final JTextField nameTextField = buildInputTextField();

    public Login(Node node) throws Exception{
        super("Login");
        this.node = node;
        listenerHandler = new ListenerHandler(node);

        JLabel chooseNameLabel = new JLabel("Choose a name for you");
        chooseNameLabel.setLabelFor(nameTextField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.onCancelButtonClicked());

        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(e -> {
            try {
                this.onConfirmButtonClicked(this.nameTextField.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Panel formPanel = new Panel();
        formPanel.add(chooseNameLabel);
        formPanel.add(nameTextField);
        formPanel.add(cancelButton);
        formPanel.add(confirmButton);

        add(formPanel, NORTH);
        add(cancelButton, WEST);
        add(confirmButton, EAST);

        nameTextField.requestFocus();
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

    private void onConfirmButtonClicked(String name) throws Exception {

        this.node.updatePeer(new Peer(name,this.node.getPeer().getHost()));

        System.out.println("start listen broadcast");
        Thread listenBroadcast = new Thread(listenerHandler);
        listenBroadcast.start();

        System.out.println("udp message send broadcast");
        new UDPMessageSenderService().sendBroadcast(node);

        this.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();

        Homepage homepage = new Homepage(this.node,this);
        homepage.display();
        System.out.println("end login");

    }

    private static JTextField buildInputTextField() {
        return new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
    }
}
