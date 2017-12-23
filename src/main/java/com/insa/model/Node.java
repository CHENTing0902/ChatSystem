package com.insa.model;

import com.insa.ui.Chat;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private Peer peer;
    private ArrayList<Peer> onlinePeers;
    private HashMap<String, Chat> chatWindowForPeer;//String -> ipAddress

    public Node(Peer peer) {
        this.peer = peer;
        this.onlinePeers = new ArrayList<>();
        this.chatWindowForPeer = new HashMap<>();
    }

    public ArrayList<Peer> getOnlinePeers() {
        return onlinePeers;
    }
  
    public Peer getPeer(){
        return this.peer;
    }

    public String userName(){
        return this.peer.getPseudonyme();
    }

    public void setChatWindowForPeer(Peer peer1,Chat chat) {
        this.chatWindowForPeer.put(peer1.getHost(),chat);
    }

    public Chat getChatWindowForPeer(String ipAddress){
        return this.chatWindowForPeer.get(ipAddress);
    }
  
    public void addPeer(Peer peer) {
        this.onlinePeers.add(peer);
        chatWindowForPeer.put(peer.getHost(),null);
    }
  
    public void updatePeersList(Peer peer1){

        if (peer1.getHost().equals(this.peer.getHost())) {
            return;
        }

        for (Peer peerInList : onlinePeers){
            if (peerInList.getHost().equals(peer1.getHost())){
                if (peerInList.getPseudonyme().equals(peer1.getPseudonyme())) {
                    return;
                }
                else{
                    peerInList.setPseudonyme(peer1.getPseudonyme());
                    return;
                }
            }
        }
        this.addPeer(peer1);
    }

    public Peer findPeerByIpAddress (String ipAddress){
        for (Peer peerInList : this.onlinePeers){
            if (peerInList.getHost().equals(ipAddress)) return peerInList;
        }
        return null;
    }

    public void update (Peer peer){
        this.peer = peer;
    }

    public String toString(){
        String str = new String ("this peer : " + this.peer.toString() + "\n" + "known peers :\n");
        for (Peer p : onlinePeers) {
            str += p.toString()+"\n";
        }
        return str;
    }
}