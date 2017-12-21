package com.insa.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Message {

    byte[] type;
    byte[] data;

    public Message(byte[] type, byte[] data){
        this.type = type;
        this.data = data;
    }

    public Message(String type, String data) {
        this(type.getBytes(), data.getBytes());
    }

    public Message(byte[] message) throws IOException {
        if (message.length < 4){
            throw new IOException("EOF in Message constructor : type");
        }
        if (message.length < 8){
            throw new IOException("EOF in Message constructor : len data");
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
    public byte[] getMsgTypeBytes() {
        return (byte[])type.clone();
    }
    public String getMsgData() {
        return new String(data);
    }
    public byte[] getMsgDataBytes() {
        return (byte[])data.clone();
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
        return "Message[" + getMsgType() + ":" + getMsgData() + "]";
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

    public static byte[] buildMessage(String type, String message) throws IOException {

        byte[] typeMessage = type.getBytes();
        byte[] data = message.getBytes();
        byte[] len = Message.intToByteArray(data.length);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write( typeMessage );
        outputStream.write( len );
        outputStream.write( data );

        return outputStream.toByteArray( );
    }


}

