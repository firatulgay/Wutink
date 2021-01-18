package com.fulgay.bilenyum.enums;

/**
 * EnumExperienceCategory
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */
public enum EnumExperienceCategory implements BaseEnum {
    TECHNOLOGY("Technology"),
    EDUCATION("Education"),
    HEALTH("Health"),
    ARTANDCULTURE("Art&Culture"),
    FOOD("Food"),
    MUSIC("Music"),
    SOCIAL("Social"),
    COSMETICS("Cosmetics"),
    OTHER("Other");

    private String type;

    EnumExperienceCategory(String type) {
        this.type = type;
    }

    @Override
    public String getValue(){
        return type;
    }


}
