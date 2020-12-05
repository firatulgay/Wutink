package com.fulgay.bilenyum.domain;

import com.fulgay.bilenyum.enums.EnumExperienceCategory;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Experience
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */
@Entity
@Table(name = "EXPERIENCE", indexes = {
        @Index(name = "IX_EXPERIENCE_HEADER",columnList = "header", unique = true)})
public class Experience {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String header;

    @Size(max = 50)
    @Column(length = 50)
    private String description;

    @Column(name = "experience_category",length = 50)
    @Enumerated(EnumType.STRING)
    private EnumExperienceCategory experienceCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", foreignKey = @ForeignKey(name = "FK_EXPERIENCE_USER"))
    private User user;


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

    public EnumExperienceCategory getExperienceCategory() {
        return experienceCategory;
    }

    public void setExperienceCategory(EnumExperienceCategory experienceCategory) {
        this.experienceCategory = experienceCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
