package com.insa.ui;

import com.insa.model.Node;
import com.insa.model.Peer;
import com.insa.network.service.UDPMessageSenderService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Chat extends JFrame {

    private JTextArea chatWindow;
    private JTextField messageTextField;

    private Homepage homepage;
    private Node node;
    private Peer cible;

    public Chat(Homepage homepage, Node node, int index) throws IndexOutOfBoundsException {
        super();

        this.homepage = homepage;
        this.node = node;
        this.cible = node.getOnlinePeers().get(index);

        setTitle("you are talking to " + this.cible.getPseudonyme());

        System.out.println("you are talking to " + this.cible.getPseudonyme());

        messageTextField = new JTextField();

        messageTextField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSendButtonClicked(messageTextField.getText());
                }
            }
        });

        add(messageTextField, BorderLayout.SOUTH);

        chatWindow = new JTextArea(20,10);
        add(new JScrollPane(chatWindow));
        setSize(300,250);

        chatWindow.setEditable(false);

        JScrollPane jScrollPanel = new JScrollPane(chatWindow);

        JButton sendPictureButton = new JButton("Send Picture");
        sendPictureButton.addActionListener(e -> onSendPictureButtonClicked());

        JButton sendFileButton = new JButton("Send File");
        sendFileButton.addActionListener(e -> onSendFileButtonClicked());

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> onSendButtonClicked(messageTextField.getText()));

        JButton closeDialogueButton = new JButton("Close Dialogue");
        closeDialogueButton.addActionListener(e -> onCloseDialogueButtonClicked());

        JPanel buttonPane = buildJPanelWith(sendPictureButton, sendFileButton, sendButton, closeDialogueButton);
        add(buttonPane, BorderLayout.PAGE_END);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                        .addComponent(messageTextField)
                                        .addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPanel, GroupLayout.Alignment.LEADING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(messageTextField))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonPane))
                        )

        );

        messageTextField.requestFocus();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private static JPanel buildJPanelWith(JButton sendPictureButton, JButton sendFileButton, JButton SendButton,
                                          JButton closeDialogueButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(sendPictureButton);
        buttonPane.add(sendFileButton);
        buttonPane.add(SendButton);
        buttonPane.add(closeDialogueButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    private void showLabel(String text)
    {
        // If text is empty return
        if(text.trim().isEmpty()) return;

        System.out.println(text);

        // Otherwise, append text with a new line
        chatWindow.append(text+"\n");

        // Set textfield and label text to empty string
        messageTextField.setText("");
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void onCloseDialogueButtonClicked() {
        this.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();
        homepage.display();
    }

    public void onSendButtonClicked(String text) {
        try {

            showLabel(text);

            System.out.println(cible.toString());
            new UDPMessageSenderService().sendMessageOn(cible, text.getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSendFileButtonClicked() {

        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Open this file: " +
                    chooser.getSelectedFile().getName());
        }

        File file = chooser.getSelectedFile();

        try {
            if (file != null) {
                System.out.println("file chosen");
                InetAddress ip = InetAddress.getLocalHost();
                int port = 12345;
                System.out.println("ready");
//                node.getPeer().sendFile(file, ip, port);
                System.out.println("send");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void onSendPictureButtonClicked() {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        File file = chooser.getSelectedFile();

        try {
            if (file != null) {
                System.out.println("file chosen");
                InetAddress ip = InetAddress.getLocalHost();
                int port = 12345;
                System.out.println("ready");
//                node.getPeer().sendFile(file, ip, port);
                System.out.println("send");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

}
