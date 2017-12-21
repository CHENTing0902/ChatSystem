package com.insa.Message;

public enum MessageType {

    JOIN("JOIN"),
    INFO("INFO"),
    MESS("MESS");

    private String typeAsString;

    MessageType (String typeAsString){
        this.typeAsString = typeAsString;
    }

    public String getTypeAsString() {
        return typeAsString;
    }
}
