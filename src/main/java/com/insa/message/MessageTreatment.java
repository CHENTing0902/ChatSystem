package com.insa.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MessageTreatment {

    byte[] type;
    byte[] data;

    public MessageTreatment(byte[] type, byte[] data){
        this.type = type;
        this.data = data;
    }

    public MessageTreatment(byte[] message) throws IOException {
        if (message.length < 4){
            throw new IOException("EOF in MessageTreatment constructor : type");
        }
        if (message.length < 8){
            throw new IOException("EOF in MessageTreatment constructor : len data");
        }

        type = new byte[4];
        byte[] len = new byte[4];
        for (int i = 0; i < 4; i++) type[i]= message[i];
        for (int i = 0; i < 4; i++) len[i] = message[i+4];

        int intLen = byteArrayToInt(len);
        System.out.println(intLen);
        data = new byte[intLen];

        for (int i=0; i<data.length; i++) data[i] = message[i+8];
    }

    public String getMsgType() {return new String(type);}
    public String getMsgData() {
        return new String(data);
    }
    public byte[] toBytes() {
        byte[] bytes = new byte[4 + 4 + data.length];
        byte[] lenbytes = intToByteArray(data.length);

        for (int i=0; i<4; i++) bytes[i] = type[i];
        for (int i=0; i<4; i++) bytes[i+4] = lenbytes[i];
        for (int i=0; i<data.length; i++) bytes[i+8] = data[i];

        return bytes;
    }

    public String toString() {
        return "MessageTreatment[" + getMsgType() + ":" + getMsgData() + "]";
    }

    public static byte[] intToByteArray (final int integer) {
        int byteNum = (40 - Integer.numberOfLeadingZeros (integer < 0 ? ~integer : integer)) / 8;
        byte[] byteArray = new byte[4];

        for (int n = 0; n < byteNum; n++)
            byteArray[3 - n] = (byte) (integer >>> (n * 8));

        return (byteArray);
    }

    public static int byteArrayToInt(byte[] byteArray) {
        int integer = 0;
        for (int n = 0; n < 4; n++) {
            integer = (integer << 8) | ( ((int)byteArray[n]) & 0xff );
        }
        return integer;
    }

    public static byte[] buildMessage(String type, byte [] message) throws IOException {

        byte[] typeMessage = type.getBytes();
        byte[] len = MessageTreatment.intToByteArray(message.length);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(typeMessage);
        outputStream.write(len);
        outputStream.write(message);

        return outputStream.toByteArray();
    }


}

