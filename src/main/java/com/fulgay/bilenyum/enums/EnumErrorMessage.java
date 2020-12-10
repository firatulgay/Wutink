package com.fulgay.bilenyum.enums;

public enum EnumErrorMessage implements BaseEnum{

    USER_NOT_FOUND("Kullanıcı bulunamadı!"),
    USER_COULDNT_SAVE("Kullanıcı kaydedilirken bir hata oluştu!");


    private String type;

    EnumErrorMessage(String type) {
        this.type=type;
    }

    @Override
    public String getDisplayValue() {
        return type;
    }
}
