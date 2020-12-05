package com.fulgay.bilenyum.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CAT2EX", indexes = {
        @Index(name = "IX_CAT2EX_ID",columnList = "id", unique = true)})
public class Cat2Ex {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Experience experience;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }
}
