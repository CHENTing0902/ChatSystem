package com.insa.Model;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class Node {

    private Peer peer;
    private ArrayList<Peer> onlinePeers;

    public Node(Peer peer) throws UnknownHostException {
        this.peer = peer;
        this.onlinePeers = new ArrayList<Peer>();

    }

    //TODO refactor node : getPseudo getHost

    public ArrayList<Peer> getOnlinePeers() {
        return onlinePeers;
    }
    public void addPeer(Peer peer) {
        this.onlinePeers.add(peer);
    }

    public Peer getPeer(){
        return this.peer;
    }

    public void updatePeer(Peer peer){
        this.peer = peer;
    }

    public String toString(){
        String str = new String ("this peer : " + this.peer.toString() + "\n" + "known peers :\n");
        for (Peer p : onlinePeers) {
            str += p.toString()+"\n";
        }
        return str;
    }

    public String userName(){
        return this.peer.getPseudonyme();
    }
}