package com.fulgay.bilenyum.commons;

import com.fulgay.bilenyum.enums.BaseEnum;

public enum EnumSuccessMessage implements BaseEnum {
    USER_SAVE_SUCCESS("Kullanıcısı başarıyla kaydedildi!"),
    LOGIN_SUCCESS("Hoş Geldiniz!"),
    CATEGORY_SAVE_SUCCESS("Kategorisi başarıyla kaydedildi!");



    private String type;

    EnumSuccessMessage(String type) {
        this.type=type;
    }

    @Override
    public String getDisplayValue() {
        return type;
    }
}
