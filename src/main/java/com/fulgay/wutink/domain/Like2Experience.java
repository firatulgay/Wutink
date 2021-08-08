package com.fulgay.wutink.domain;

import javax.persistence.*;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@Entity
@Table(name = "LIKE2EXPERIENCE", indexes = {
        @Index(name = "IX_LIKE2EX_ID",columnList = "id", unique = true)})
public class Like2Experience {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Experience experience;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
