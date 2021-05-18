package com.casestudy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hastag")
public class Hastag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hastagId;

    @NotBlank
    @Size(max = 50)
    private String hastagName;

    @ManyToMany(mappedBy = "hastags")
    private Set<Topic> topics = new HashSet<>();

    public Hastag() {}
    public Hastag(Long hastagId, @NotBlank @Size(max = 50) String hastagName) {
        this.hastagId = hastagId;
        this.hastagName = hastagName;
    }

    public Hastag(@NotBlank @Size(max = 50) String hastagName, Set<Topic> topics) {
        this.hastagName = hastagName;
        this.topics = topics;
    }

    public Hastag(@NotBlank @Size(max = 50) String hastagName) {
        this.hastagName = hastagName;
    }

    public Long getHastagId() {
        return hastagId;
    }

    public void setHastagId(Long hastagId) {
        this.hastagId = hastagId;
    }

    public String getHastagName() {
        return hastagName;
    }

    public void setHastagName(String name) {
        this.hastagName = name;
    }
}
