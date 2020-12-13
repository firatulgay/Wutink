package com.fulgay.bilenyum.commons;

import com.fulgay.bilenyum.enums.BaseEnum;

public enum EnumErrorMessage implements BaseEnum {

    USER_NOT_FOUND("Kullanıcı bulunamadı!"),
    USER_COULDNT_SAVE("Kullanıcı kaydedilirken bir hata oluştu!"),
    USERNAME_ALREADY_EXIST("Kullanıcı adı alınmış!");

    private String type;

    EnumErrorMessage(String type) {
        this.type=type;
    }

    @Override
    public String getDisplayValue() {
        return type;
    }
}
