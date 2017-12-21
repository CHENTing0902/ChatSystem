package com.insa.model;

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
  
    public Peer getPeer(){
        return this.peer;
    }

    public String userName(){
        return this.peer.getPseudonyme();
    }
  
    public void addPeer(Peer peer) {
        this.onlinePeers.add(peer);
    }
  
    public void updatePeersList(Peer peer){

        if (peer.getHost().equals(this.peer.getHost())) {
            return;
        }
        
        for (int i = 0; i < onlinePeers.size(); i++){
            if (onlinePeers.get(i).getHost().equals(peer.getHost())){
                if (onlinePeers.get(i).getPseudonyme().equals(peer.getPseudonyme())) {
                    return;
                }
                else{
                    onlinePeers.get(i).setPseudonyme(peer.getPseudonyme());
                    return;
                }
            }
        }
        onlinePeers.add(peer);

    }

    //TODO change method name updatePeer
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
}