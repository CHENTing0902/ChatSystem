package com.insa.file;

import com.insa.model.Node;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler implements Runnable {

    private Node node;
    private byte[] fileAsByte;
    private String ipAddress;

    public FileHandler(Node node, byte[] data, String ipAddress) {
        this.node = node;
        this.fileAsByte = data;
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        System.out.println("CALL IN FileHandler :\nReceived a file from " + this.ipAddress + "\n");

        try {
            FileOutputStream fos = new FileOutputStream("/home/tn_van/Bureau/file.pdf");
            fos.write(fileAsByte);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
