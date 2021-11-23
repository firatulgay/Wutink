package com.fulgay.wutink.commons.notificationMessages;

public class GlobalMessages {
    private EnumMessageType messageType;
    private String message;


    public GlobalMessages() {
    }

    public GlobalMessages(EnumMessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public EnumMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(EnumMessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
