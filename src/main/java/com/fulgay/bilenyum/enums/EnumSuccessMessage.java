package com.fulgay.bilenyum.enums;

public enum EnumSuccessMessage implements BaseEnum{
    USER_SAVE_SUCCESS("Kullanıcı kaydedildi!");

    private String type;


    EnumSuccessMessage(String type) {
        this.type=type;
    }

    @Override
    public String getDisplayValue() {
        return type;
    }
}
