package com.insa.Model;

import java.net.*;

public class Peer {

    public final static int PORT_RECEIVE = 8888 ;
    public final static int PORT_SEND = 6666 ;

    private String pseudonyme;
    private String host;
    private int port;

    public Peer(String host) throws UnknownHostException {
        this.host = host;
        this.port = PORT_RECEIVE;
    }

    public Peer(String pseudonyme, String host) throws UnknownHostException {
        this.pseudonyme = pseudonyme;
        this.host = host;
        this.port = PORT_RECEIVE;
    }

    public Peer(String pseudonyme, String host, int port) throws UnknownHostException {
        this.pseudonyme = pseudonyme;
        this.host = host;
        this.port = port;
    }

    public void setPseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setHost(String host){
        this.host = host;
    }

    public String getHost(){
        return this.host;
    }

    public int getPort(){
        return this.port;
    }

    public String toString(){
        return "id "+ this.pseudonyme + " ("+ this.host+ " : "+ this.port+ ")";
    }
}
