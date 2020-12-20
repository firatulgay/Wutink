package com.fulgay.bilenyum.commons;

public class GlobalMessages {
    private  String ERROR_MESSAGE;
    private  String INFO_MESSAGE;
    private String CONF_MESSAGE;

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    public void setErrorMessage(String errorMessage) {
        ERROR_MESSAGE = errorMessage;
    }

    public String getInfoMessage() {
        return INFO_MESSAGE;
    }

    public void setInfoMessage(String infoMessage) {
        INFO_MESSAGE = infoMessage;
    }

    public String getConfMessage() {
        return CONF_MESSAGE;
    }

    public void setConfMessage(String CONF_MESSAGE) {
        this.CONF_MESSAGE = CONF_MESSAGE;
    }
}
