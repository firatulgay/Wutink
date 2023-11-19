package com.fulgay.wutink.dtos;

import java.util.Date;
import java.util.List;

public class ExperienceDto extends BaseDto {
    private Long id;
    private String header;
    private String description;
    private List<Long> categoryIdList;
    private String userName;
    private String creationTimeStr;

    private Date creationTimeDate;
    private Long likeCount;
    private Long commentCount;

    private List<String> categoryNameList;

    public ExperienceDto() {
    }

    public ExperienceDto(Long id, String header, String description, String userName, Date creationTimeDate, Long likeCount, Long commentCount) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.userName = userName;
        this.creationTimeDate = creationTimeDate;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public Date getCreationTimeDate() {
        return creationTimeDate;
    }

    public void setCreationTimeDate(Date creationTimeDate) {
        this.creationTimeDate = creationTimeDate;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

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

    public String getCreationTimeStr() {
        return creationTimeStr;
    }

    public void setCreationTimeStr(String creationTimeStr) {
        this.creationTimeStr = creationTimeStr;
    }

    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public List<String> getCategoryNameList() {
        return this.categoryNameList;
    }
}
