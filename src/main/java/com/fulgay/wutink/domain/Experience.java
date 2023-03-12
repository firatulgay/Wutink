package com.fulgay.wutink.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Experience
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */
@Entity
@Table(name = "EXPERIENCE", indexes = {@Index(name = "IX_EXPERIENCE_HEADER",columnList = "header,ID_USER", unique = true)})
public class Experience {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @Column
    private Date creationTime;

    @Column
    private Date modifiedTime;

    @Column
    private String header;

    @Column(name = "description",length = 1500)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
