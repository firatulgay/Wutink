package com.fulgay.bilenyum.commons;

import com.fulgay.bilenyum.enums.BaseEnum;

public enum EnumErrorMessage implements BaseEnum {

    USER_NOT_FOUND("Kullanıcı bulunamadı!","100"),
    USER_COULDNT_SAVE("Kullanıcı kaydedilirken bir hata oluştu!","101"),
    USERNAME_ALREADY_EXIST("Kullanıcı adı alınmış!","102"),
    GENERAL_ERROR("İşlem sırasında hata oluştu","103"),
    LOGIN_ERROR("Kullanıcı adı veya Şifre hatalı","104");


    private String type;
    private String code;

    EnumErrorMessage(String type, String code) {
        this.type=type;
        this.code=code;
    }

    @Override
    public String getDisplayValue() {
        return type;
    }

    public  String  getCode(){
        return code;
    }
}
