package com.insa.network;

import java.io.IOException;

public class IncomingMessageTreater implements IncomingMessageListener{

    @Override
    public void onNewIncomingMessage(byte[] message) throws IOException {
        System.out.println(new String(message));
    }
}
