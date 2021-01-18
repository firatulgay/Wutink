package com.fulgay.bilenyum.enums;

/**
 * EnumUserType
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */
public enum EnumUserType implements BaseEnum{
    USER("User"),
    ADMIN("Admin");

    private final String type;

    EnumUserType(String type) {
        this.type = type;
    }

    @Override
    public String getValue(){
        return type;
    }
}
