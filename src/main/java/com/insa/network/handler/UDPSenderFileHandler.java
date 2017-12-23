package com.insa.network.handler;

import com.insa.message.MessageTreatment;
import com.insa.model.Peer;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class UDPSenderFileHandler implements Runnable {

    private Peer peer;
    private File sendFile;
    private int dataLen = 1024;


    public UDPSenderFileHandler(Peer peer, File file) {
        this.peer = peer;
        this.sendFile = file;
    }

    @Override
    public void run() {

            try {
                if(sendFile.isFile()) {

                    byte[] sendBuff=new byte[dataLen];

                    DatagramSocket datagramSocket = new DatagramSocket();
                    DatagramPacket datagramPacket = new DatagramPacket(sendBuff,0,
                            InetAddress.getByName(this.peer.getHost()),peer.getPort());

                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sendFile));

                    while ((bis.read(sendBuff)) > 0) {

                        byte [] message = MessageTreatment.buildMessage("FILE", sendBuff);

                        datagramPacket.setData(message, 0, message.length);
                        datagramSocket.send(datagramPacket);
                        TimeUnit.MICROSECONDS.sleep(10);
                    }
                    bis.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}

