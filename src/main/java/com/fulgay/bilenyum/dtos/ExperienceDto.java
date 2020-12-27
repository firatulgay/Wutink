package com.fulgay.bilenyum.dtos;

public class ExperienceDto extends BaseDto {

    private Long id;
    private String header;
    private String description;
    private CategoryDto categoryDto;
    private ExperienceDto experienceDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public ExperienceDto getExperienceDto() {
        return experienceDto;
    }

    public void setExperienceDto(ExperienceDto experienceDto) {
        this.experienceDto = experienceDto;
    }
}
