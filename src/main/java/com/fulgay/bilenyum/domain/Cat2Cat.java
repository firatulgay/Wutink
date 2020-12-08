package com.fulgay.bilenyum.domain;

import javax.persistence.*;

@Entity
@Table(name = "CAT2CAT", indexes = {
        @Index(name = "IX_CAT2CAT_ID",columnList = "id", unique = true)})
public class Cat2Cat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category superCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }
}
