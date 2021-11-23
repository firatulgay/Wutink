package com.fulgay.wutink.commons.notificationMessages;

import com.fulgay.wutink.enums.BaseEnum;

/**
 * @author Fırat ÜLGAY
 * @since 24.11.2021
 */

public enum EnumMessageType implements BaseEnum {
    ERROR_MESSAGE,
    INFO_MESSAGE,
    CONF_MESSAGE;

    @Override
    public String getValue() {
        return this.name();
    }
}
