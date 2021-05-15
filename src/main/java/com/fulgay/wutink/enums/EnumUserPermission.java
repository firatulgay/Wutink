package com.fulgay.wutink.enums;

/**
 * @author Fırat ÜLGAY
 * @since 3.05.2021
 */

public enum EnumUserPermission implements BaseEnum {

    ACCESS_TEST1("ACCESS_TEST1"),
    ACCESS_TEST2("ACCESS_TEST2");

    private final String type;

    EnumUserPermission(String type) {
        this.type=type;
    }

    @Override
    public String getValue() {
        return type;
    }
}
