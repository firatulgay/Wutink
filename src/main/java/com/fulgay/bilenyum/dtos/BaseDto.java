package com.fulgay.bilenyum.dtos;

import com.fulgay.bilenyum.commons.GlobalMessages;

public class BaseDto {

    private  GlobalMessages globalMessage;

    public GlobalMessages getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(GlobalMessages globalMessage) {
        this.globalMessage = globalMessage;
    }
}
