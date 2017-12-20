package com.insa.network;

import java.io.IOException;

public interface IncomingMessageListener {
    void onNewIncomingMessage(byte[] messageInByteArray) throws IOException;
}
