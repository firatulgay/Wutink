package com.fulgay.wutink.dtos.response.experience;

import java.util.List;

public class ExperienceCategoryContainerDto {
    private Long categoryId;
    private List<ExperienceDto> experienceDtoList;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ExperienceDto> getExperienceDtoList() {
        return experienceDtoList;
    }

    public void setExperienceDtoList(List<ExperienceDto> experienceDtoList) {
        this.experienceDtoList = experienceDtoList;
    }
}
