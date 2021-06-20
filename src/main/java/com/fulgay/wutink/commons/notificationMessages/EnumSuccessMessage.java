package com.fulgay.wutink.commons.notificationMessages;

import com.fulgay.wutink.enums.BaseEnum;

public enum EnumSuccessMessage implements BaseEnum {

    REGISTER_SUCCESS("Kayıt olduğunuz için teşekkür ederiz!"),
    LOGIN_SUCCESS("Hoş Geldiniz!"),
    CATEGORY_SAVE_SUCCESS("Kategorisi başarıyla kaydedildi!"),
    EXPERIENCE_SAVE_SUCCESS("Deneyim başarıyla kaydedildi!");

    private String type;

    EnumSuccessMessage(String type) {
        this.type=type;
    }

    @Override
    public String getValue() {
        return type;
    }
}
