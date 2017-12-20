package com.insa.ui;

import com.insa.Model.Node;
import com.insa.Model.Peer;

public class ChatWindowTest {

    Chat chat;
    public ChatWindowTest() throws Exception {
        Node node = new Node(new Peer("lili", "localhost"));
        node.addPeer(new Peer("lili", "localhost"));
        Login login = new Login(node);
        Homepage homepage= new Homepage(node, login);
        chat = new Chat(homepage, node, 0);
    }

    public void display () {
        chat.display();
    }

    public static void main (String [] args) {
        try {
            ChatWindowTest chatWindowTest = new ChatWindowTest();
            chatWindowTest.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
