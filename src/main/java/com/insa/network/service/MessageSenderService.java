package com.insa.network.service;

import com.insa.Model.Node;
import com.insa.Model.Peer;

public interface MessageSenderService {

    /**
     * Send message to given peer
     * @param peer      the peer to send to
     * @param message   the message to send to peer
     * @throws Exception
     */
    void sendMessageOn(Peer peer, String message) throws Exception;
}
