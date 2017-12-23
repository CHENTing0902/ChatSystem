package com.insa.message;

public enum MessageType {

    JOIN("JOIN"),
    INFO("INFO"),
    MESS("MESS"),
    FILE("FILE");

    private String typeAsString;

    MessageType (String typeAsString){
        this.typeAsString = typeAsString;
    }

    public String getTypeAsString() {
        return typeAsString;
    }
}
