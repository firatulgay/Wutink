package com.fulgay.wutink.dtos.response.experience;

import com.fulgay.wutink.dtos.BaseDto;

import java.util.List;

public class UserExperienceResponseDto extends BaseDto {

    private List<ExperienceCategoryContainerDto> experienceCategoryContainerDtoList;

    public List<ExperienceCategoryContainerDto> getExperienceContainerResponseDtoList() {
        return experienceCategoryContainerDtoList;
    }

    public void setExperienceContainerResponseDtoList(List<ExperienceCategoryContainerDto> experienceCategoryContainerDtoList) {
        this.experienceCategoryContainerDtoList = experienceCategoryContainerDtoList;
    }
}
