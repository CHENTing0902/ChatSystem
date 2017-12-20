package com.insa.ui;


import com.insa.model.Node;
import com.insa.network.service.UDPMessageSenderService;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;

public class ChangeName extends JFrame {

    private static final int DEFAULT_TEXT_FIELD_WIDTH = 10;
    private Homepage homepage;
    private Node node;
    private final JTextField chooseNameTextField = buildInputTextField();

    public ChangeName(Homepage homepage, Node node) throws Exception{
        super("Chat System");

        this.homepage = homepage;
        this.node = node;

        JLabel chooseNameLabel = new JLabel("Write new name for you");
        chooseNameLabel.setLabelFor(chooseNameTextField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> onCancelButtonClicked());

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> onConfirmButtonClicked(chooseNameTextField.getText()));

        Panel formPanel = new Panel();
        formPanel.add(chooseNameLabel);
        formPanel.add(chooseNameTextField);
        formPanel.add(cancelButton);
        formPanel.add(confirmButton);

        add(formPanel, NORTH);
        add(cancelButton, WEST);
        add(confirmButton, EAST);

        chooseNameTextField.requestFocus();
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
        homepage.display();
    }

    private void onConfirmButtonClicked(String newName) {

        node.getPeer().setPseudonyme(newName);

        //TODO broadcast to all online users
        try {

            new UDPMessageSenderService().sendBroadcast(this.node);

            this.setVisible(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.dispose();

            homepage.setTitle("Chat System : " + this.node.getPeer().getPseudonyme().toString());
            homepage.display();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JTextField buildInputTextField() {
        return new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
    }

}


