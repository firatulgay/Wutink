package com.fulgay.wutink.domain;

import javax.persistence.*;

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
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EXPERIENCE", foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
