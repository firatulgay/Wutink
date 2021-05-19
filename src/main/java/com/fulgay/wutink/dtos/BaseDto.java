package com.fulgay.wutink.dtos;

import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;

public class BaseDto {

    private  GlobalMessages globalMessage;
    private boolean success;

    public GlobalMessages getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(GlobalMessages globalMessage) {
        this.globalMessage = globalMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
