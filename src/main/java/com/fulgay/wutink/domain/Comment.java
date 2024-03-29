package com.fulgay.wutink.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Entity
@Table(name = "COMMENT")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @Column(name = "description",length = 1500)
    private String description;

    @Column
    private Date creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER", foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EXPERIENCE", foreignKey = @ForeignKey(name = "FK_COMMENT_EXPERIENCE"))
    private Experience experience;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
