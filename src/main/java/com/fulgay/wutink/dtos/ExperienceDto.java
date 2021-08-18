package com.fulgay.wutink.dtos;

import java.util.List;

public class ExperienceDto extends BaseDto {

    private Long id;
    private String header;
    private String description;
    private List<Long> categoryIdList;
    private String userName; ;

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

    public List<Long> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
