package com.fulgay.wutink.dtos;

import com.fulgay.wutink.commons.GlobalMessages;

public class BaseDto {

    private  GlobalMessages globalMessage;

    public GlobalMessages getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(GlobalMessages globalMessage) {
        this.globalMessage = globalMessage;
    }
}
