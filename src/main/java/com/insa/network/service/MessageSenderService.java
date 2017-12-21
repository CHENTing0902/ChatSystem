package com.insa.network.service;

import com.insa.Message.MessageType;
import com.insa.model.Peer;

public interface MessageSenderService {

    /**
     * Send message to given peer
     * @param peer      the peer to send to
     * @param message   the message to send to peer
     * @throws Exception
     */
    void sendMessageOn(Peer peer, byte [] message, MessageType type) throws Exception;
}
