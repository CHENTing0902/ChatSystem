package com.insa.ui;

import com.insa.model.Node;
import com.insa.model.Peer;

import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame{

    private final JList<String> list;
    private final DefaultListModel<String> listModel;

    private Node node;
    private Login ui;

    public Homepage(Node node1, Login ui) {
        super();

        this.node = node1;
        this.ui = ui;

        System.out.println("Node : " + this.node.toString());

        this.list = buildJList();
        this.listModel = new DefaultListModel<>();
        list.setModel(listModel);

        for (Peer p : node.getOnlinePeers()){
            listModel.addElement(p.toString());
        }

        setTitle("Chat system : "+ this.node.getPeer().getPseudonyme().toString());

        list.addListSelectionListener(e -> updateOnLineButtonState());
        JScrollPane listScrollPane = new JScrollPane(this.list);

        JButton startChatButton = new JButton("Start Chat");
        startChatButton.addActionListener(e -> onStartChatButtonClicked());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> onUpdateButton());

        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(e -> onChangeNameButtonClicked());

        JPanel buttonPane = buildJPanelWith(startChatButton, updateButton, changeNameButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateOnLineButtonState() {
    }

    private static JList<String> buildJList() {
        JList<String> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        return list;
    }

    private static JPanel buildJPanelWith(JButton startChatButton, JButton updateButton, JButton changeNameButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(startChatButton);
        buttonPane.add(updateButton);
        buttonPane.add(changeNameButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void onUpdateButton() {

        listModel.clear();
        for (Peer p : node.getOnlinePeers()){
            listModel.addElement(p.toString());
        }

        list.setModel(listModel);
    }

    //TODO address already in use
    private void onChangeNameButtonClicked() {
        this.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();

        try{
            ChangeName name = new ChangeName(this, this.node);
            name.display();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onStartChatButtonClicked() {
        if (list.isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "alert", "You should choose a user!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            this.setVisible(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.dispose();

            int index ;
            index = list.getSelectedIndex();
            Chat chat = new Chat(this, node, index);
            chat.display();

        }

    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
    //TODO choisir le nom et commencer la conversation

}

