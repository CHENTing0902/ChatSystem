package com.insa.Message;

public enum MessageType {

    JOIN("JOIN");

    private String typeAsString;

    MessageType (String typeAsString){
        this.typeAsString = typeAsString;
    }
}
